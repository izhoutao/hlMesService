package com.haili.basic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.JournalingRewindItemMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.service.IJournalingRewindItemService;
import com.haili.framework.domain.basic.JournalingRewindItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.response.JournalingShiftReportCode;
import com.haili.framework.domain.basic.response.OutboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.WorkflowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
 */
@Service
@Transactional
public class JournalingRewindItemServiceImpl extends ServiceImpl<JournalingRewindItemMapper, JournalingRewindItem> implements IJournalingRewindItemService {
    /*    @Override
        public IPage<JournalingRewindItem> page(IPage<JournalingRewindItem> page, Wrapper<JournalingRewindItem> queryWrapper) {
            return this.baseMapper.selectPagePreload(page, queryWrapper);
        }

        @Override
        public List<JournalingRewindItem> list(Wrapper<JournalingRewindItem> queryWrapper) {
            return this.baseMapper.selectListPreload(queryWrapper);
        }*/
    @Autowired
    InboundOrderRawItemServiceImpl inboundOrderRawItemServiceImpl;
/*    @Autowired
    InboundOrderRawMapper inboundOrderRawMapper;*/
    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;

    @Override
    public boolean save(JournalingRewindItem entity) {
//        setSteelGradeAndHotRollOrigin(entity);
        super.save(entity);
        String productNumber = entity.getProductNumber();
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, "重卷");
        OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_CHOOSE_THIS_PRODUCT_NUMBER);
        }
        outboundOrderRawItem.setCurrentOperationLabel("重卷");
        String jsonTextWorkflow = outboundOrderRawItem.getJsonTextWorkflow();
        Map nextWorkflowContext = WorkflowUtil.getNextWorkflowContext(jsonTextWorkflow, "重卷", "OK");
        if (nextWorkflowContext == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        String label = (String) nextWorkflowContext.get("label");
        outboundOrderRawItem.setNextOperationLabel(label);

        String operationHistory = outboundOrderRawItem.getOperationHistory();
        HashMap<String, String> map = new HashMap<>();
        map.put("itemId", entity.getId());
        map.put("operationName", "重卷");
        List<Map> maps = JSON.parseArray(operationHistory, Map.class);
        if (maps == null) {
            maps = new ArrayList<>();
        }
        maps.add(map);
        operationHistory = JSON.toJSONString(maps);
        outboundOrderRawItem.setOperationHistory(operationHistory);
        outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
        return true;
    }

/*    private void setSteelGradeAndHotRollOrigin(JournalingRewindItem entity) {
        String productNumber = entity.getProductNumber();
        InboundOrderRawItem inboundOrderRawItem = inboundOrderRawItemServiceImpl.getByOutboundRawItemProductNumber(productNumber);
        String inboundOrderRawId = inboundOrderRawItem.getInboundOrderRawId();
        InboundOrderRaw inboundOrderRaw = inboundOrderRawMapper.selectById(inboundOrderRawId);
        String hotRollOrigin = inboundOrderRaw.getHotRollOrigin();
        entity.setHotRollOrigin(hotRollOrigin);
        String steelGrade = inboundOrderRawItem.getSteelGrade();
        entity.setSteelGrade(steelGrade);
    }*/

    @Override
    public boolean updateById(JournalingRewindItem entity) {
        String id = entity.getId();
        JournalingRewindItem journalingRewindItem = this.baseMapper.selectById(id);
        if (journalingRewindItem == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Integer status = journalingRewindItem.getStatus();
        if (status != 0) {
            ExceptionCast.cast(JournalingShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_MODIFY);
        }
        String productNumber = journalingRewindItem.getProductNumber();
        if (!productNumber.equals(entity.getProductNumber())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
//        setSteelGradeAndHotRollOrigin(entity);
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        JournalingRewindItem journalingRewindItem = this.baseMapper.selectById(id);
        Integer status = journalingRewindItem.getStatus();
        if (status != 0) {
            ExceptionCast.cast(JournalingShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_DELETE);
        }
        String productNumber = journalingRewindItem.getProductNumber();
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);

        lambdaQueryWrapper.ne(OutboundOrderRawItem::getNextOperationLabel, "");
        OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        String operationHistory = outboundOrderRawItem.getOperationHistory();
        List<Map> maps = JSON.parseArray(operationHistory, Map.class);
        int size = maps.size();
        if (size == 0) {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        Map map = maps.get(size - 1);
        String itemId = (String) map.get("itemId");
        if (!StrUtil.equals(itemId, (String) id)) {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        String previousOperation;
        if (size == 1) {
            previousOperation = "";
        } else {
            previousOperation = (String) (maps.get(size - 2).get("operationName"));
        }
        outboundOrderRawItem.setCurrentOperationLabel(previousOperation);
        outboundOrderRawItem.setNextOperationLabel("重卷");
        maps.remove(size - 1);
        outboundOrderRawItem.setOperationHistory(JSON.toJSONString(maps));
        outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
        return super.removeById(id);
    }
}
