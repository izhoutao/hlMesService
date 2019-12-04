package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderDetailMapper;
import com.haili.basic.mapper.InboundOrderMapper;
import com.haili.basic.service.IInboundOrderService;
import com.haili.framework.domain.basic.InboundOrder;
import com.haili.framework.domain.basic.InboundOrderDetail;
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
 * @since 2019-11-20
 */
@Service
@Transactional
public class InboundOrderServiceImpl extends ServiceImpl<InboundOrderMapper, InboundOrder> implements IInboundOrderService {
    @Autowired
    InboundOrderDetailMapper inboundOrderDetailMapper;

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<InboundOrderDetail> lambdaQueryWrapper = Wrappers.<InboundOrderDetail>lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderDetail::getInboundOrderId, id);
        return inboundOrderDetailMapper.delete(lambdaQueryWrapper) >= 0;
    }
}
