package com.haili.basic.controller;

import com.haili.framework.domain.basic.Order;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
