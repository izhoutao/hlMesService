package com.haili.ucenter.controller;

import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-24
*/
@RestController
@RequestMapping("/ucenter/role")
public class RoleController extends CrudController<Role> {

}
