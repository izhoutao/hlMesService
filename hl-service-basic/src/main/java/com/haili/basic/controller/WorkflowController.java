package com.haili.basic.controller;

import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-17
*/
@RestController
@RequestMapping("/basic/workflow")
public class WorkflowController extends CrudController<Workflow> implements WorkflowControllerApi{

}
