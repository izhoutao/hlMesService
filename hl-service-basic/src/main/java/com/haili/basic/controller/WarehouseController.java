package com.haili.basic.controller;

import com.haili.framework.domain.basic.Warehouse;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
