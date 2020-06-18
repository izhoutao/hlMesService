package com.haili.basic.controller;

import com.haili.framework.domain.basic.ShipOrder;
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
 * @since 2020-03-06
*/
@RestController
@RequestMapping("/basic/shiporder")
public class ShipOrderController extends CrudController<ShipOrder> {
    @Override
//    @PreAuthorize("hasAuthority('ship_order_list')")
    public QueryResponseResult<ShipOrder> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('ship_order_save')")
    public ModelResponseResult<ShipOrder> save(@RequestBody ShipOrder entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('ship_order_update')")
    public ResponseResult updateById(@RequestBody ShipOrder entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('ship_order_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
