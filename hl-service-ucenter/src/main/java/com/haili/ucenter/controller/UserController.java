package com.haili.ucenter.controller;

import com.haili.framework.domain.ucenter.User;
import com.haili.framework.domain.ucenter.response.UserResult;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-24
 */
@RestController
@RequestMapping("/ucenter/user")
public class UserController extends CrudController<User> {

    @GetMapping
    public UserResult getUserByUserName(@RequestParam("userName") String userName) {
        User user = ((UserServiceImpl) getService()).getUserByUserName(userName);
        return new UserResult(CommonCode.SUCCESS, user);
    }

}
