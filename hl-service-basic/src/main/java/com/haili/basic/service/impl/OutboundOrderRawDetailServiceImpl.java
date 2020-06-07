package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.OutboundOrderRawDetailMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.mapper.OutboundOrderRawMapper;
import com.haili.basic.service.IOutboundOrderRawDetailService;
import com.haili.basic.service.IOutboundOrderRawItemService;
import com.haili.framework.domain.basic.OutboundOrderRaw;
import com.haili.framework.domain.basic.OutboundOrderRawDetail;
import com.haili.framework.domain.basic.response.OutboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-19
 */
@Service
@Transactional
public class OutboundOrderRawDetailServiceImpl extends ServiceImpl<OutboundOrderRawDetailMapper, OutboundOrderRawDetail> implements IOutboundOrderRawDetailService {
    @Autowired
    private OutboundOrderRawMapper outboundOrderRawMapper;
    @Autowired
    private OutboundOrderRawItemMapper outboundOrderRawItemMapper;

    @Autowired
    private IOutboundOrderRawItemService outboundOrderRawItemService;

    @Override
    public IPage<OutboundOrderRawDetail> page(IPage<OutboundOrderRawDetail> page, Wrapper<OutboundOrderRawDetail> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<OutboundOrderRawDetail> list(Wrapper<OutboundOrderRawDetail> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }

    @Override
    public boolean save(OutboundOrderRawDetail entity) {
        String outboundOrderRawId = entity.getOutboundOrderRawId();
        OutboundOrderRaw outboundOrderRaw = outboundOrderRawMapper.selectById(outboundOrderRawId);
        if (outboundOrderRaw == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_RECEIPT_DOES_NOT_EXIST);
        }
        entity.setWorkOrderNumber(outboundOrderRaw.getWorkOrderNumber());
        if (entity.getOutQuantity() == null) {
            entity.setOutQuantity(0);
        }
        return super.save(entity);
    }


    @Override
    public boolean updateById(OutboundOrderRawDetail entity) {
        entity.setOutboundOrderRawId(null).setWorkOrderNumber(null).setQuantity(null);
        return super.updateById(entity);
    }

/*
    @Override
    public boolean removeById(Serializable id) {
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getOutboundOrderRawDetailId, id);
        outboundOrderRawItemMapper.delete(lambdaQueryWrapper);
        return super.removeById(id);
    }*/
}
