package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderMapper;
import com.haili.basic.service.IInboundOrderService;
import com.haili.framework.domain.basic.InboundOrder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-20
 */
@Service
public class InboundOrderServiceImpl extends ServiceImpl<InboundOrderMapper, InboundOrder> implements IInboundOrderService {

}
