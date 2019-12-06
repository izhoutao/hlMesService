package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InspectionRuleMaterialMapper;
import com.haili.basic.service.IInspectionRuleMaterialService;
import com.haili.framework.domain.basic.InspectionRuleMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-05
 */
@Service
public class InspectionRuleMaterialServiceImpl extends ServiceImpl<InspectionRuleMaterialMapper, InspectionRuleMaterial> implements IInspectionRuleMaterialService {
    @Override
    public IPage<InspectionRuleMaterial> page(IPage<InspectionRuleMaterial> page, Wrapper<InspectionRuleMaterial> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<InspectionRuleMaterial> list(Wrapper<InspectionRuleMaterial> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }
}
