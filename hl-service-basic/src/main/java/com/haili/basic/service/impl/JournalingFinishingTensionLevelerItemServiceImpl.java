package com.haili.basic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawMapper;
import com.haili.basic.mapper.JournalingFinishingTensionLevelerItemMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.service.IJournalingFinishingTensionLevelerItemService;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.JournalingFinishingTensionLevelerItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.response.JournalingShiftReportCode;
import com.haili.framework.domain.basic.response.OutboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.WorkflowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;
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
public class JournalingFinishingTensionLevelerItemServiceImpl extends ServiceImpl<JournalingFinishingTensionLevelerItemMapper, JournalingFinishingTensionLevelerItem> implements IJournalingFinishingTensionLevelerItemService {

    @Autowired
    InboundOrderRawMapper inboundOrderRawMapper;
    @Autowired
    InboundOrderRawItemServiceImpl inboundOrderRawItemServiceImpl;
    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;

    @Override
    public boolean save(JournalingFinishingTensionLevelerItem entity) {
        setSteelGradeAndCostTimeAndOutputWeightLoss(entity);
        super.save(entity);
        String productNumber = entity.getProductNumber();
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, "精整拉矫");
        OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_CHOOSE_THIS_PRODUCT_NUMBER);
        }
        outboundOrderRawItem.setCurrentOperationLabel("精整拉矫");
        String jsonTextWorkflow = outboundOrderRawItem.getJsonTextWorkflow();
        Map nextWorkflowContext = WorkflowUtil.getNextWorkflowContext(jsonTextWorkflow, "精整拉矫", "OK");
        if (nextWorkflowContext == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        String label = (String) nextWorkflowContext.get("label");
        outboundOrderRawItem.setNextOperationLabel(label);

        String operationHistory = outboundOrderRawItem.getOperationHistory();
        HashMap<String, String> map = new HashMap<>();
        map.put("itemId", entity.getId());
        map.put("operationName", "精整拉矫");
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


    private void setSteelGradeAndCostTimeAndOutputWeightLoss(JournalingFinishingTensionLevelerItem entity) {
        String productNumber = entity.getProductNumber();
        InboundOrderRawItem inboundOrderRawItem = inboundOrderRawItemServiceImpl.getByOutboundRawItemProductNumber(productNumber);
        String steelGrade = inboundOrderRawItem.getSteelGrade();
        entity.setSteelGrade(steelGrade);
        String surfaceFinish = inboundOrderRawItem.getSurfaceFinish();
        entity.setSurfaceFinish(surfaceFinish);
        Duration between = Duration.between(entity.getBeginTime(), entity.getEndTime());
        long minutes = between.toMinutes();
        entity.setCostTime(minutes);
        entity.setOutputWeightLoss(entity.getOutputWeight() - entity.getInputWeight());
    }

    @Override
    public boolean updateById(JournalingFinishingTensionLevelerItem entity) {
        String id = entity.getId();
        JournalingFinishingTensionLevelerItem journalingFinishingTensionLevelerItem = this.baseMapper.selectById(id);
        if (journalingFinishingTensionLevelerItem == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Integer status = journalingFinishingTensionLevelerItem.getStatus();
        if (status != 0) {
            ExceptionCast.cast(JournalingShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_MODIFY);
        }
        String productNumber = journalingFinishingTensionLevelerItem.getProductNumber();
        if (!productNumber.equals(entity.getProductNumber())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        setSteelGradeAndCostTimeAndOutputWeightLoss(entity);
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        JournalingFinishingTensionLevelerItem journalingFinishingTensionLevelerItem = this.baseMapper.selectById(id);
        Integer status = journalingFinishingTensionLevelerItem.getStatus();
        if (status != 0) {
            ExceptionCast.cast(JournalingShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_DELETE);
        }
        String productNumber = journalingFinishingTensionLevelerItem.getProductNumber();
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
        outboundOrderRawItem.setNextOperationLabel("精整拉矫");
        maps.remove(size - 1);
        outboundOrderRawItem.setOperationHistory(JSON.toJSONString(maps));
        outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
        return super.removeById(id);
    }

}
