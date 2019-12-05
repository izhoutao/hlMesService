package com.haili.basic.controller;

import com.haili.framework.domain.basic.InspectionRule;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-05
*/
@RestController
@RequestMapping("/basic/inspectionrule")
public class InspectionRuleController extends CrudController<InspectionRule> {

}
