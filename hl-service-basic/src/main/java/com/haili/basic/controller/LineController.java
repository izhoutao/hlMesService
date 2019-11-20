package com.haili.basic.controller;

import com.haili.framework.domain.basic.Line;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-18
*/
@RestController
@RequestMapping("/basic/line")
public class LineController extends CrudController<Line> {

}
