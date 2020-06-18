package com.haili.basic.controller;

import com.haili.framework.domain.basic.OrderItem;
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
 * @since 2019-12-16
*/
@RestController
@RequestMapping("/basic/orderitem")
public class OrderItemController extends CrudController<OrderItem> {
    @Override
//    @PreAuthorize("hasAuthority('order_item_list')")
    public QueryResponseResult<OrderItem> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('order_item_save')")
    public ModelResponseResult<OrderItem> save(@RequestBody OrderItem entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('order_item_update')")
    public ResponseResult updateById(@RequestBody OrderItem entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('order_item_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
