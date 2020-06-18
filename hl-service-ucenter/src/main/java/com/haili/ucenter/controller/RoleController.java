package com.haili.ucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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

    @Override
//    @PreAuthorize("hasAuthority('role_list')")
    public QueryResponseResult<Role> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('role_save')")
    public ModelResponseResult<Role> save(@RequestBody Role entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('role_update')")
    public ResponseResult updateById(@RequestBody Role entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('role_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
