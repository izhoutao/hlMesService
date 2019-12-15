package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.IqcMapper;
import com.haili.basic.service.IIqcService;
import com.haili.framework.domain.basic.Iqc;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-13
 */
@Service
public class IqcServiceImpl extends ServiceImpl<IqcMapper, Iqc> implements IIqcService {
    @Override
    public IPage<Iqc> page(IPage<Iqc> page, Wrapper<Iqc> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<Iqc> list(Wrapper<Iqc> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }
}
