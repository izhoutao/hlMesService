package com.haili.basic.controller;

import com.haili.framework.domain.basic.Order;
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
@RequestMapping("/basic/order")
public class OrderController extends CrudController<Order> {
    @Override
//    @PreAuthorize("hasAuthority('order_list')")
    public QueryResponseResult<Order> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('order_save')")
    public ModelResponseResult<Order> save(@RequestBody Order entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('order_update')")
    public ResponseResult updateById(@RequestBody Order entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('order_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
