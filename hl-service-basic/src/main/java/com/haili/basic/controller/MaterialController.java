package com.haili.basic.controller;

import com.haili.framework.domain.basic.Material;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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
//    @PreAuthorize("hasAuthority('material_list')")
    public QueryResponseResult<Material> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('material_save')")
    public ModelResponseResult<Material> save(@RequestBody Material entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('material_update')")
    public ResponseResult updateById(@RequestBody Material entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('material_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
