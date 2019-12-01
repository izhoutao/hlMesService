package com.haili.ucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.ucenter.Role;
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
 * @since 2019-11-24
*/
@RestController
@RequestMapping("/ucenter/role")
public class RoleController extends CrudController<Role> {
    @Override
    protected QueryWrapper<Role> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(map.get("name")), "name", map.get("name"));
        return queryWrapper;
    }
}