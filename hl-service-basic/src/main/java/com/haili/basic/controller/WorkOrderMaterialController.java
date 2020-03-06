package com.haili.basic.controller;

import com.haili.framework.domain.basic.WorkOrderMaterial;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-02-27
*/
@RestController
@RequestMapping("/basic/workordermaterial")
public class WorkOrderMaterialController extends CrudController<WorkOrderMaterial> {

}
