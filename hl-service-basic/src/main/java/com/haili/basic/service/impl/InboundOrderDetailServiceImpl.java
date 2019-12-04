package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderDetailMapper;
import com.haili.basic.service.IInboundOrderDetailService;
import com.haili.framework.domain.basic.InboundOrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-02
 */
@Service
public class InboundOrderDetailServiceImpl extends ServiceImpl<InboundOrderDetailMapper, InboundOrderDetail> implements IInboundOrderDetailService {
    @Override
    public IPage<InboundOrderDetail> page(IPage<InboundOrderDetail> page, Wrapper<InboundOrderDetail> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<InboundOrderDetail> list(Wrapper<InboundOrderDetail> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }
}
