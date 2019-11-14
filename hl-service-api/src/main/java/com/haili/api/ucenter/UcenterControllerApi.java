package com.haili.api.ucenter;

import com.haili.framework.domain.ucenter.ext.MenuDTO;
import com.haili.framework.domain.ucenter.ext.RoleExt;
import com.haili.framework.domain.ucenter.ext.UserExt;
import com.haili.framework.domain.ucenter.ext.UserSearchParam;
import com.haili.framework.domain.ucenter.response.RoleResult;
import com.haili.framework.domain.ucenter.response.UserResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "用户中心", description = "用户中心管理")
public interface UcenterControllerApi {
//    @ApiOperation("获取用户信息")
//    public UserExt getUserext(String username);


    QueryResponseResult findUserList(long page, long size, UserSearchParam userSearchParam);

    @ApiOperation("获取用户信息")
    UserResult findUserExtByUserName(String userName);

    ResponseResult addUser(UserExt userExt);

    ResponseResult updateUser(String userId, UserExt userExt);

    ResponseResult deleteUser(String userId);

    @ApiOperation("角色列表查询")
    public QueryResponseResult findRoleList(long page, long size);

//    @ApiOperation("角色查询")
//    public RoleResult findRoleExtById(String roleId);

    @ApiOperation("添加角色信息")
    public RoleResult addRole(RoleExt roleExt);

    @ApiOperation("更新角色信息")
    public ResponseResult updateRole(String roleId, RoleExt roleExt);

    @ApiOperation("删除角色信息")
    public ResponseResult deleteRole(String roleId);


    List<MenuDTO> findMenuList();
}
