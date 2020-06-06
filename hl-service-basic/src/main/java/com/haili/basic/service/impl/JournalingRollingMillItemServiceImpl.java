package com.haili.basic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.InboundOrderRawMapper;
import com.haili.basic.mapper.JournalingRollingMillItemMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.service.IJournalingRollingMillItemService;
import com.haili.framework.domain.basic.InboundOrderRaw;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.JournalingRollingMillItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.response.JournalingProductionShiftReportCode;
import com.haili.framework.domain.basic.response.OutboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.WorkflowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @since 2020-03-30
 */
@Service
public class JournalingRollingMillItemServiceImpl extends ServiceImpl<JournalingRollingMillItemMapper, JournalingRollingMillItem> implements IJournalingRollingMillItemService {
    @Autowired
    InboundOrderRawItemMapper inboundOrderRawItemMapper;
    @Autowired
    InboundOrderRawMapper inboundOrderRawMapper;

    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;

    @Override
    public boolean save(JournalingRollingMillItem entity) {
        setSteelGrade(entity);
        super.save(entity);
        String productNumber = entity.getProductNumber();
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, "轧机");
        OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_CHOOSE_THIS_PRODUCT_NUMBER);
        }
        outboundOrderRawItem.setCurrentOperationLabel("轧机");
        String jsonTextWorkflow = outboundOrderRawItem.getJsonTextWorkflow();
        Map nextWorkflowContext = WorkflowUtil.getNextWorkflowContext(jsonTextWorkflow, "轧机", "OK");
        if (nextWorkflowContext == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        String label = (String) nextWorkflowContext.get("label");
        outboundOrderRawItem.setNextOperationLabel(label);

        String operationHistory = outboundOrderRawItem.getOperationHistory();
        HashMap<String, String> map = new HashMap<>();
        map.put("itemId", entity.getId());
        map.put("operationName", "轧机");
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

    private void setSteelGrade(JournalingRollingMillItem entity) {
        String productNumber = entity.getProductNumber();
        LambdaQueryWrapper<InboundOrderRawItem> lambdaQueryWrapper = Wrappers.<InboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderRawItem::getProductNumber, productNumber).orderByDesc(InboundOrderRawItem::getTime);
        InboundOrderRawItem inboundOrderRawItem = inboundOrderRawItemMapper.selectList(lambdaQueryWrapper).get(0);
        String inboundOrderRawId = inboundOrderRawItem.getInboundOrderRawId();
        InboundOrderRaw inboundOrderRaw = inboundOrderRawMapper.selectById(inboundOrderRawId);
        String steelGrade = inboundOrderRawItem.getSteelGrade();
        entity.setSteelGrade(steelGrade);
    }

    @Override
    public boolean updateById(JournalingRollingMillItem entity) {
        String id = entity.getId();
        JournalingRollingMillItem journalingRollingMillItem = this.baseMapper.selectById(id);
        if (journalingRollingMillItem == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Integer status = journalingRollingMillItem.getStatus();
        if (status != 0) {
            ExceptionCast.cast(JournalingProductionShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_MODIFY);
        }
        String productNumber = journalingRollingMillItem.getProductNumber();
        if (!productNumber.equals(entity.getProductNumber())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        setSteelGrade(entity);
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        JournalingRollingMillItem journalingRollingMillItem = this.baseMapper.selectById(id);
        Integer status = journalingRollingMillItem.getStatus();
        if (status != 0) {
            ExceptionCast.cast(JournalingProductionShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_DELETE);
        }
        String productNumber = journalingRollingMillItem.getProductNumber();
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
        outboundOrderRawItem.setNextOperationLabel("轧机");
        maps.remove(size - 1);
        outboundOrderRawItem.setOperationHistory(JSON.toJSONString(maps));
        outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
        return super.removeById(id);
    }

}
