package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.JournalingRewindItemMapper;
import com.haili.basic.service.IJournalingRewindItemService;
import com.haili.framework.domain.basic.JournalingRewindItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
 */
@Service
public class JournalingRewindItemServiceImpl extends ServiceImpl<JournalingRewindItemMapper, JournalingRewindItem> implements IJournalingRewindItemService {
    @Override
    public IPage<JournalingRewindItem> page(IPage<JournalingRewindItem> page, Wrapper<JournalingRewindItem> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<JournalingRewindItem> list(Wrapper<JournalingRewindItem> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }
}
