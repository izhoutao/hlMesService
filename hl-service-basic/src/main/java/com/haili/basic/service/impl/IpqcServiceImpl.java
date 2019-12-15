package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.IpqcMapper;
import com.haili.basic.service.IIpqcService;
import com.haili.framework.domain.basic.Ipqc;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-15
 */
@Service
public class IpqcServiceImpl extends ServiceImpl<IpqcMapper, Ipqc> implements IIpqcService {
    @Override
    public IPage<Ipqc> page(IPage<Ipqc> page, Wrapper<Ipqc> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<Ipqc> list(Wrapper<Ipqc> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }
}
