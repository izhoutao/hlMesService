package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.InboundOrderRawMapper;
import com.haili.basic.mapper.JournalingAnnealItemMapper;
import com.haili.basic.service.IJournalingAnnealItemService;
import com.haili.framework.domain.basic.InboundOrderRaw;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.JournalingAnnealItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-30
 */
@Service
public class JournalingAnnealItemServiceImpl extends ServiceImpl<JournalingAnnealItemMapper, JournalingAnnealItem> implements IJournalingAnnealItemService {
    @Autowired
    InboundOrderRawItemMapper inboundOrderRawItemMapper;
    @Autowired
    InboundOrderRawMapper inboundOrderRawMapper;
    @Override
    public boolean save(JournalingAnnealItem entity) {
        setSteelGradeAndCostTimeAndOutputWeightLoss(entity);
        return super.save(entity);
    }

    private void setSteelGradeAndCostTimeAndOutputWeightLoss(JournalingAnnealItem entity) {
        String productNumber = entity.getProductNumber();
        LambdaQueryWrapper<InboundOrderRawItem> lambdaQueryWrapper = Wrappers.<InboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderRawItem::getProductNumber, productNumber);
        InboundOrderRawItem inboundOrderRawItem = inboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        String inboundOrderRawId = inboundOrderRawItem.getInboundOrderRawId();
        InboundOrderRaw inboundOrderRaw = inboundOrderRawMapper.selectById(inboundOrderRawId);
        String steelGrade = inboundOrderRawItem.getSteelGrade();
        entity.setSteelGrade(steelGrade);
        Duration between = Duration.between(entity.getBeginTime(), entity.getEndTime());
        long minutes = between.toMinutes();
        entity.setCostTime(minutes);
        entity.setOutputWeightLoss(entity.getOutputWeight() - entity.getInputWeight());
    }

    @Override
    public boolean updateById(JournalingAnnealItem entity) {
        setSteelGradeAndCostTimeAndOutputWeightLoss(entity);
        return super.updateById(entity);
    }
}
