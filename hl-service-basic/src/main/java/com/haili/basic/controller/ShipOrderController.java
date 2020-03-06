package com.haili.basic.controller;

import com.haili.framework.domain.basic.ShipOrder;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
