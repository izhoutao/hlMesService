package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.dao.DefectGroupMapper;
import com.haili.basic.dao.DefectMapper;
import com.haili.basic.service.IDefectGroupService;
import com.haili.framework.domain.basic.Defect;
import com.haili.framework.domain.basic.DefectGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-15
 */
@Service
public class DefectGroupServiceImpl extends ServiceImpl<DefectGroupMapper, DefectGroup> implements IDefectGroupService {
    @Autowired
    DefectMapper defectMapper;

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<Defect> lambdaQueryWrapper = Wrappers.<Defect>lambdaQuery();
        lambdaQueryWrapper.eq(Defect::getGroupId, id);
        return defectMapper.delete(lambdaQueryWrapper) >= 0;
    }
}
