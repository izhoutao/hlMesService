package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.OrderItem;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    protected QueryWrapper<OrderItem> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<OrderItem> orderItemQueryWrapper = super.extractWrapperFromRequestMap(map);
        orderItemQueryWrapper.eq("order_id", map.get("orderId"));
        return orderItemQueryWrapper;
    }
}
