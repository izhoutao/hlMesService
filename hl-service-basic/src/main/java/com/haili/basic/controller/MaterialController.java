package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.Material;
import com.haili.framework.web.CrudController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/basic/material")
public class MaterialController extends CrudController<Material> {
    @Override
    protected QueryWrapper<Material> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(map.get("name")), "name", map.get("name"))
                .eq(!StringUtils.isEmpty(map.get("typeId")), "type_id", map.get("typeId"));
        return queryWrapper;
    }
}
