package com.haili.ucenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.ucenter.User;
import com.haili.framework.domain.ucenter.response.UserResult;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.UserServiceImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
//    @Override
//    @
//    public ModelResponseResult<User> save(User entity) {
//        return super.save(entity);
//    }

    @GetMapping
    public UserResult getUserByUserName(@RequestParam("userName") String userName) {
        User user = ((UserServiceImpl) getService()).getUserByUserName(userName);
        return new UserResult(CommonCode.SUCCESS, user);
    }

    @Override
    protected QueryWrapper<User> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Object phone = map.get("phone");
        Object depts = map.get("depts");
        queryWrapper.like(!StringUtils.isEmpty(phone), "phone", phone)
                .in((!StringUtils.isEmpty(depts))
                                && (depts instanceof List)
                                && !((List) depts).isEmpty()
                        , "dept_id",
                        (!StringUtils.isEmpty(depts)) && (depts instanceof List) ? ((List) depts).toArray() : null);
        return queryWrapper;
    }
}
