package com.haili.basic.controller;

import com.haili.framework.domain.basic.MaterialType;
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
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-21
*/
@RestController
@RequestMapping("/basic/materialtype")
public class MaterialTypeController extends CrudController<MaterialType> {
    @Override
//    @PreAuthorize("hasAuthority('material_type_list')")
    public QueryResponseResult<MaterialType> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('material_type_save')")
    public ModelResponseResult<MaterialType> save(@RequestBody MaterialType entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('material_type_update')")
    public ResponseResult updateById(@RequestBody MaterialType entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('material_type_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
