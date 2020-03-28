package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.InboundOrderRawMapper;
import com.haili.basic.mapper.JournalingRewindItemMapper;
import com.haili.basic.service.IJournalingRewindItemService;
import com.haili.framework.domain.basic.InboundOrderRaw;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.JournalingRewindItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    public boolean save(JournalingRewindItem entity) {
        setSteelGradeAndHotRollOrigin(entity);
        return super.save(entity);
    }

    private void setSteelGradeAndHotRollOrigin(JournalingRewindItem entity) {
        String productNumber = entity.getProductNumber();
        LambdaQueryWrapper<InboundOrderRawItem> lambdaQueryWrapper = Wrappers.<InboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderRawItem::getProductNumber, productNumber);
        InboundOrderRawItem inboundOrderRawItem = inboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        String inboundOrderRawId = inboundOrderRawItem.getInboundOrderRawId();
        InboundOrderRaw inboundOrderRaw = inboundOrderRawMapper.selectById(inboundOrderRawId);
        String hotRollOrigin = inboundOrderRaw.getHotRollOrigin();
        entity.setHotRollOrigin(hotRollOrigin);
        String steelGrade = inboundOrderRawItem.getSteelGrade();
        entity.setSteelGrade(steelGrade);
    }

    @Override
    public boolean updateById(JournalingRewindItem entity) {
        setSteelGradeAndHotRollOrigin(entity);
        return super.updateById(entity);
    }
}
