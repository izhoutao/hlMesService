package com.haili.ucenter.controller;

import com.haili.api.ucenter.UcenterControllerApi;
import com.haili.framework.domain.ucenter.ext.*;
import com.haili.framework.domain.ucenter.response.RoleResult;
import com.haili.framework.domain.ucenter.response.UserResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.ucenter.service.MenuService;
import com.haili.ucenter.service.RoleService;
import com.haili.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/ucenter")
public class UcenterController implements UcenterControllerApi {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Override
    @GetMapping("/user/list/{page}/{size}")
    public QueryResponseResult findUserList(@PathVariable("page") long page, @PathVariable("size")long size, UserSearchParam userSearchParam) {
        return userService.findUserList(page, size, userSearchParam);
    }

    @Override
    @GetMapping("/user/{userName}")
    public UserResult findUserExtByUserName(@PathVariable("userName") String userName) {
        return userService.findUserExtByUserName(userName);
    }

    @Override
    @PostMapping("/user")
    public UserResult addUser(@RequestBody UserExt userExt) {
        return userService.addUser(userExt);
    }

    @Override
    @PutMapping("/user/{userId}")
    public ResponseResult updateUser(@PathVariable("userId") String userId, @RequestBody UserExt userExt) {
        return userService.updateUser(userId, userExt);
    }

    @Override
    @DeleteMapping("/user/{userId}")
    public ResponseResult deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUser(userId);
    }

    @Override
    @GetMapping("/role/list/{page}/{size}")
    public QueryResponseResult findRoleList(@PathVariable("page") long page, @PathVariable("size")long size) {
        return roleService.findRoleList(page, size);
    }

//    @Override
//    @GetMapping("/role/{roleId}")
//    public RoleResult findRoleExtById(@PathVariable("roleId") String roleId) {
//        return roleService.findRoleExtById(roleId);
//    }

    @Override
    @PostMapping("/role")
    public RoleResult addRole(@RequestBody RoleExt roleExt) {
        return roleService.addRole(roleExt);
    }

    @Override
    @PutMapping("/role/{roleId}")
    public ResponseResult updateRole(@PathVariable("roleId")  String roleId, @RequestBody RoleExt roleExt) {
        return roleService.updateRole(roleId, roleExt);
    }

    @Override
    @DeleteMapping("/role/{roleId}")
    public ResponseResult deleteRole(@PathVariable("roleId") String roleId) {
        return roleService.deleteRole(roleId);
    }

    @Override
    @GetMapping("/menu/list")
    public List<MenuDTO> findMenuList(){
        return menuService.findMenuList();
    }
}
