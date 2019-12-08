package com.haili.ucenter.controller;

import com.haili.framework.web.CrudController;
import com.haili.framework.domain.system.CodeRule;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-07
*/
@RestController
@RequestMapping("/ucenter/coderule")
public class CodeRuleController extends CrudController<CodeRule> {

}
