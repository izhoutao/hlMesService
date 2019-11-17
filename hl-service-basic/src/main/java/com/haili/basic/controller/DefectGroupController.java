package com.haili.basic.controller;

import com.haili.framework.domain.basic.DefectGroup;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-15
*/
@RestController
@RequestMapping("/basic/defectgroup")
public class DefectGroupController extends CrudController<DefectGroup> {

}
