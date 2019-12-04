package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.OutboundOrderDetailMapper;
import com.haili.basic.service.IOutboundOrderDetailService;
import com.haili.framework.domain.basic.OutboundOrderDetail;
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
public class OutboundOrderDetailServiceImpl extends ServiceImpl<OutboundOrderDetailMapper, OutboundOrderDetail> implements IOutboundOrderDetailService {
    @Override
    public IPage<OutboundOrderDetail> page(IPage<OutboundOrderDetail> page, Wrapper<OutboundOrderDetail> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<OutboundOrderDetail> list(Wrapper<OutboundOrderDetail> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }
}
