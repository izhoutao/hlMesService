package com.haili.basic.controller;

import com.haili.framework.domain.basic.QcDefect;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-04-02
*/
@RestController
@RequestMapping("/basic/qc-defect")
public class QcDefectController extends CrudController<QcDefect> {

}
