package com.haili.basic.controller;

import com.haili.framework.domain.basic.Printer;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-03
*/
@RestController
@RequestMapping("/basic/printer")
public class PrinterController extends CrudController<Printer> {

}
