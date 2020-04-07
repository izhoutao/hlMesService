package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.InboundOrderRawMapper;
import com.haili.basic.mapper.JournalingRewindItemMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.service.IJournalingRewindItemService;
import com.haili.framework.domain.basic.InboundOrderRaw;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.JournalingRewindItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.response.IpqcCode;
import com.haili.framework.domain.basic.response.JournalingProductionShiftReportCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

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
    InboundOrderRawItemMapper inboundOrderRawItemMapper;
    @Autowired
    InboundOrderRawMapper inboundOrderRawMapper;
    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;

    @Override
    public boolean save(JournalingRewindItem entity) {
        String productNumber = entity.getProductNumber();
        updateOutboundOrderRawItem(productNumber, 1);
        setSteelGradeAndHotRollOrigin(entity);
        return super.save(entity);
    }

    private void updateOutboundOrderRawItem(String productNumber, Integer nextOperationStatus) {
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, "重卷");
        OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(IpqcCode.IPQC_INSPECTOR_RESULT_CANNOT_BE_MODIFIED);
        }
        outboundOrderRawItem.setNextOperationStatus(nextOperationStatus);
        outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
    }

    private void setSteelGradeAndHotRollOrigin(JournalingRewindItem entity) {
        String productNumber = entity.getProductNumber();
        LambdaQueryWrapper<InboundOrderRawItem> lambdaQueryWrapper = Wrappers.<InboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderRawItem::getProductNumber, productNumber).orderByDesc(InboundOrderRawItem::getTime);
        InboundOrderRawItem inboundOrderRawItem = inboundOrderRawItemMapper.selectList(lambdaQueryWrapper).get(0);
        String inboundOrderRawId = inboundOrderRawItem.getInboundOrderRawId();
        InboundOrderRaw inboundOrderRaw = inboundOrderRawMapper.selectById(inboundOrderRawId);
        String hotRollOrigin = inboundOrderRaw.getHotRollOrigin();
        entity.setHotRollOrigin(hotRollOrigin);
        String steelGrade = inboundOrderRawItem.getSteelGrade();
        entity.setSteelGrade(steelGrade);
    }

    @Override
    public boolean updateById(JournalingRewindItem entity) {
        String id = entity.getId();
        JournalingRewindItem journalingRewindItem = this.baseMapper.selectById(id);
        Integer status = journalingRewindItem.getStatus();
        if(status!=0){
            ExceptionCast.cast(JournalingProductionShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_MODIFY);
        }
        String productNumber = journalingRewindItem.getProductNumber();
        if(!productNumber.equals(entity.getProductNumber())){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        updateOutboundOrderRawItem(productNumber, 1);
        setSteelGradeAndHotRollOrigin(entity);
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        JournalingRewindItem journalingRewindItem = this.baseMapper.selectById(id);
        Integer status = journalingRewindItem.getStatus();
        if(status!=0){
            ExceptionCast.cast(JournalingProductionShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_DELETE);
        }
        updateOutboundOrderRawItem(journalingRewindItem.getProductNumber(), 0);
        return super.removeById(id);
    }
}
