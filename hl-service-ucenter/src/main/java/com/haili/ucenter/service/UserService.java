package com.haili.ucenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.domain.ucenter.User;
import com.haili.framework.domain.ucenter.UserRole;
import com.haili.framework.domain.ucenter.ext.UserExt;
import com.haili.framework.domain.ucenter.ext.UserSearchParam;
import com.haili.framework.domain.ucenter.response.UcenterCode;
import com.haili.framework.domain.ucenter.response.UserResult;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.QueryResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.utils.BCryptUtil;
import com.haili.ucenter.mapper.RoleMapper;
import com.haili.ucenter.mapper.UserMapper;
import com.haili.ucenter.mapper.UserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
@Transactional
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;

    //根据账号查询用户信息
    public UserResult findUserExtByUserName(String username) {
        if (username == null || StringUtils.isEmpty(username)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //根据用户名查询用户
//        QueryWrapper<User> wrapper = new QueryWrapper<User>();
//        wrapper.eq("username", username);
//        User user = userMapper.selectOne(wrapper);
//        if (user == null) {
//            return null;
//        }
//        //根据用户id查询用户角色
//        String userId = user.getId();
//        List<Role> roleList = roleMapper.findRoleListByUserId(userId);
//        UserExt userExt = new UserExt();
//        BeanUtils.copyProperties(user, userExt);
//        //用户的权限
//        userExt.setRoles(roleList);
        UserExt userExt = userMapper.findUserExtByUserName(username);
        return new UserResult(CommonCode.SUCCESS, userExt);
    }

    public QueryResponseResult findUserList(long page, long size, UserSearchParam userSearchParam) {

        Page<User> _page = new Page<>(page, size);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("name", userSearchParam.getName());
        map.put("phone", userSearchParam.getPhone());
        map.put("username", userSearchParam.getUsername());
        userQueryWrapper.allEq(map, false);

        IPage<User> userIPage = userMapper.selectPage(_page, userQueryWrapper);
        List<UserExt> userExts = userIPage.getRecords().stream().map(user -> {
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.eq("user_id", user.getId());
            List<UserRole> userRoles = userRoleMapper.selectList(userRoleQueryWrapper);
            List<Role> roleList = userRoles.stream().map(userRole -> {
                QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
                roleQueryWrapper.eq("id", userRole.getRoleId());
                return roleMapper.selectOne(roleQueryWrapper);
            }).collect(Collectors.toList());
            UserExt userExt = new UserExt();
            BeanUtils.copyProperties(user, userExt);
            userExt.setRoleList(roleList);
            userExt.setPassword("");
            return userExt;
        }).collect(Collectors.toList());
        QueryResult<UserExt> userQueryResult = new QueryResult<>();
        userQueryResult.setList(userExts);
        userQueryResult.setTotal(userIPage.getTotal());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, userQueryResult);
    }

    public UserResult addUser(UserExt userExt) {
        if (userExt == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        if (StringUtils.isBlank(userExt.getUsername())) {
            ExceptionCast.cast(UcenterCode.UCENTER_USERNAME_NONE);
        }
        if (StringUtils.isBlank(userExt.getPassword())) {
            ExceptionCast.cast(UcenterCode.UCENTER_PASSWORD_NONE);
        }
        LocalDateTime now = LocalDateTime.now();
        userExt.setCreateTime(now);
        userExt.setUpdateTime(now);
        //密码加密
        String newPassword = BCryptUtil.encode(userExt.getPassword());//加密后的密码
        userExt.setPassword(newPassword);
        userMapper.insert(userExt);
        // 前端传来的roleList里包括id
        userExt.getRoleList().stream().map(
                (role) -> {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(role.getId());
                    userRole.setUserId(userExt.getId());
                    userRole.setCreateTime(LocalDateTime.now());
                    return userRole;
                }
        ).forEach(userRoleMapper::insert);

//         前端传来的roleList里只有roleCode而不包括id的话
//        Map<String, Object> saveParams = new HashMap<>();
//        saveParams.put("userId", userExt.getId());
////        saveParams.put("menuIds", roleExt.getMenuList().stream().map(Menu::getId).collect(Collectors.toList()));
//        saveParams.put("roleIds", userExt.getRoleList().stream().map((role) -> {
//            QueryWrapper<Role> roleWrapper = new QueryWrapper<>();
//            roleWrapper.eq("role_code", role.getRoleCode());
//            Role role1 = roleMapper.selectOne(roleWrapper);
//            return role1.getId();
//        }).collect(Collectors.toList()));
//        userRoleMapper.saveUserRole(saveParams);

        return new UserResult(CommonCode.SUCCESS, userExt);
    }

    public ResponseResult updateUser(String userId, UserExt userExt) {
        if (StringUtils.isBlank(userId) || userExt == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }

        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        userRoleMapper.delete(wrapper);
        userExt.setId(userId);
        LocalDateTime now = LocalDateTime.now();
        userExt.setUpdateTime(now);
        //密码加密
        String newPassword = BCryptUtil.encode(userExt.getPassword());//加密后的密码
        userExt.setPassword(newPassword);
        userMapper.updateById(userExt);
        userExt.getRoleList().stream().map(
                (role) -> {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(role.getId());
                    userRole.setUserId(userExt.getId());
                    userRole.setCreateTime(LocalDateTime.now());
                    return userRole;
                }
        ).forEach(userRoleMapper::insert);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public ResponseResult deleteUser(String userId) {
        if (StringUtils.isEmpty(userId)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        userRoleMapper.delete(wrapper);
        userMapper.deleteById(userId);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
