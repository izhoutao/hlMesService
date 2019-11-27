package com.haili.api.ucenter;

import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.domain.ucenter.User;
import com.haili.framework.domain.ucenter.ext.MenuDTO;
import com.haili.framework.domain.ucenter.response.RoleResult;
import com.haili.framework.domain.ucenter.response.UserResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "用户中心", description = "用户中心管理")
public interface UcenterControllerApi {

//    QueryResponseResult findUserList(long page, long size, UserSearchParam userSearchParam);

    @ApiOperation("获取用户信息")
    UserResult getUserByUserName(String userName);

    ResponseResult addUser(User user);

    ResponseResult updateUser(String userId, User user);

    ResponseResult deleteUser(String userId);

    @ApiOperation("角色列表查询")
    public QueryResponseResult findRoleList(long page, long size);

    @ApiOperation("添加角色信息")
    public RoleResult addRole(Role role);

    @ApiOperation("更新角色信息")
    public ResponseResult updateRole(String roleId, Role role);

    @ApiOperation("删除角色信息")
    public ResponseResult deleteRole(String roleId);


    List<MenuDTO> findMenuList();
}
