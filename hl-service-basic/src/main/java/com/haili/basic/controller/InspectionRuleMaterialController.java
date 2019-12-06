package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.InspectionRuleMaterial;
import com.haili.framework.web.CrudController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-05
*/
@RestController
@RequestMapping("/basic/inspectionrulematerial")
public class InspectionRuleMaterialController extends CrudController<InspectionRuleMaterial> {
    @Override
    protected QueryWrapper<InspectionRuleMaterial> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<InspectionRuleMaterial> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("inspectionRuleId")), "inspection_rule_id", map.get("inspectionRuleId"));
        return queryWrapper;
    }
}
