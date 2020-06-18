package com.haili.basic.controller;

import com.haili.framework.domain.basic.Warehouse;
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
 * @since 2019-11-20
*/
@RestController
@RequestMapping("/basic/warehouse")
public class WarehouseController extends CrudController<Warehouse> {
    @Override
//    @PreAuthorize("hasAuthority('warehouse_list')")
    public QueryResponseResult<Warehouse> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('warehouse_save')")
    public ModelResponseResult<Warehouse> save(@RequestBody Warehouse entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('warehouse_update')")
    public ResponseResult updateById(@RequestBody Warehouse entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('warehouse_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
