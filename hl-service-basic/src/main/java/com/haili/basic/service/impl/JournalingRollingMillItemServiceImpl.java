package com.haili.basic.service.impl;

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
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
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
        String productNumber = entity.getProductNumber();
        updateOutboundOrderRawItem(productNumber,1);
        setSteelGrade(entity);
        return super.save(entity);
    }
    private void updateOutboundOrderRawItem(String productNumber, Integer nextOperationStatus) {
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, "轧机");
        OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        outboundOrderRawItem.setNextOperationStatus(nextOperationStatus);
        outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
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
        Integer status = journalingRollingMillItem.getStatus();
        if(status!=0){
            ExceptionCast.cast(JournalingProductionShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_MODIFY);
        }
        String productNumber = entity.getProductNumber();
        if(!productNumber.equals(entity.getProductNumber())){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        updateOutboundOrderRawItem(productNumber,1);
        setSteelGrade(entity);
        return super.updateById(entity);
    }
    @Override
    public boolean removeById(Serializable id) {
        JournalingRollingMillItem journalingRollingMillItem = this.baseMapper.selectById(id);
        Integer status = journalingRollingMillItem.getStatus();
        if(status!=0){
            ExceptionCast.cast(JournalingProductionShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_DELETE);
        }
        updateOutboundOrderRawItem(journalingRollingMillItem.getProductNumber(), 0);
        return super.removeById(id);
    }

}
