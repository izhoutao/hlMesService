package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InspectionRuleItemMapper;
import com.haili.basic.mapper.InspectionRuleMapper;
import com.haili.basic.mapper.InspectionRuleMaterialMapper;
import com.haili.basic.service.IInspectionRuleService;
import com.haili.framework.domain.basic.InspectionRuleItem;
import com.haili.framework.domain.basic.InspectionRule;
import com.haili.framework.domain.basic.InspectionRuleMaterial;
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
 * @since 2019-12-05
 */
@Service
@Transactional
public class InspectionRuleServiceImpl extends ServiceImpl<InspectionRuleMapper, InspectionRule> implements IInspectionRuleService {
    @Autowired
    InspectionRuleItemMapper inspectionRuleItemMapper;
    @Autowired
    InspectionRuleMaterialMapper inspectionRuleMaterialMapper;

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<InspectionRuleItem> lambdaQueryWrapper = Wrappers.<InspectionRuleItem>lambdaQuery();
        lambdaQueryWrapper.eq(InspectionRuleItem::getInspectionRuleId, id);
        LambdaQueryWrapper<InspectionRuleMaterial> lambdaQueryWrapper1 = Wrappers.<InspectionRuleMaterial>lambdaQuery();
        lambdaQueryWrapper1.eq(InspectionRuleMaterial::getInspectionRuleId, id);
        inspectionRuleItemMapper.delete(lambdaQueryWrapper);
        inspectionRuleMaterialMapper.delete(lambdaQueryWrapper1);
        return true;
    }
}
