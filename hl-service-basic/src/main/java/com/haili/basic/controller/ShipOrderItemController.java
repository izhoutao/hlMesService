package com.haili.basic.controller;

import com.haili.framework.domain.basic.ShipOrderItem;
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
@RequestMapping("/basic/shiporderitem")
public class ShipOrderItemController extends CrudController<ShipOrderItem> {
    @Override
//    @PreAuthorize("hasAuthority('ship_order_item_list')")
    public QueryResponseResult<ShipOrderItem> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('ship_order_item_save')")
    public ModelResponseResult<ShipOrderItem> save(@RequestBody ShipOrderItem entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('ship_order_item_update')")
    public ResponseResult updateById(@RequestBody ShipOrderItem entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('ship_order_item_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
