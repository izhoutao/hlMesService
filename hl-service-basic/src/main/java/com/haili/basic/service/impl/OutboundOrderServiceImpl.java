package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.OutboundOrderDetailMapper;
import com.haili.basic.mapper.OutboundOrderMapper;
import com.haili.basic.service.IOutboundOrderService;
import com.haili.framework.domain.basic.OutboundOrderDetail;
import com.haili.framework.domain.basic.OutboundOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-02
 */
@Service
public class OutboundOrderServiceImpl extends ServiceImpl<OutboundOrderMapper, OutboundOrder> implements IOutboundOrderService {
    @Autowired
    OutboundOrderDetailMapper outboundOrderDetailMapper;

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<OutboundOrderDetail> lambdaQueryWrapper = Wrappers.<OutboundOrderDetail>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderDetail::getOutboundOrderId, id);
        return outboundOrderDetailMapper.delete(lambdaQueryWrapper) >= 0;
    }
}
