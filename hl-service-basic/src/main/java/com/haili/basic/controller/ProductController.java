package com.haili.basic.controller;

import com.haili.framework.domain.basic.Product;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-21
*/
@RestController
@RequestMapping("/basic/product")
public class ProductController extends CrudController<Product> {

}
