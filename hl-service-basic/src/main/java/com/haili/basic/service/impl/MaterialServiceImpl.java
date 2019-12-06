package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.MaterialMapper;
import com.haili.basic.service.IMaterialService;
import com.haili.framework.domain.basic.Material;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-21
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {
//    @Override
//    public IPage<Material> page(IPage<Material> page, Wrapper<Material> queryWrapper) {
//        return this.baseMapper.selectPagePreload(page, queryWrapper);
//    }
//
//    @Override
//    public List<Material> list(Wrapper<Material> queryWrapper) {
//        return this.baseMapper.selectListPreload(queryWrapper);
//    }

}
