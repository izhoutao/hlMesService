package com.haili.basic.controller;

import com.haili.framework.domain.basic.InboundOrderProductItem;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-05-30
*/
@RestController
@RequestMapping("/basic/inboundorderproductitem")
public class InboundOrderProductItemController extends CrudController<InboundOrderProductItem> {

}