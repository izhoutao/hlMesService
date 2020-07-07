package com.haili.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.basic.Department;
import com.haili.framework.domain.ucenter.User;
import com.haili.framework.domain.ucenter.UserRole;
import com.haili.framework.domain.ucenter.ext.UserPassVo;
import com.haili.framework.domain.ucenter.response.UcenterCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.BCryptUtil;
import com.haili.framework.utils.HlOauth2Util;
import com.haili.ucenter.mapper.DepartmentMapper;
import com.haili.ucenter.mapper.UserMapper;
import com.haili.ucenter.mapper.UserRoleMapper;
import com.haili.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    //根据账号查询用户信息
    public User getUserByUserName(String username) {
        if (username == null || StringUtils.isEmpty(username)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        User user = this.baseMapper.getUserByUserName(username);
        Department department = departmentMapper.selectById(user.getDeptId());
        user.setDepartment(department.getName());
        return user;
    }


    @Override
    public IPage<User> page(IPage<User> page, Wrapper<User> queryWrapper) {
        IPage<User> userIPage = this.baseMapper.selectPagePreload(page, queryWrapper);
        List<User> users = userIPage.getRecords()
                .stream()
                .map(user -> user.setPassword(""))
                .collect(Collectors.toList());
        userIPage.setRecords(users);
        return userIPage;
    }

    @Override
    public List<User> list(Wrapper<User> queryWrapper) {
        List<User> users = this.baseMapper.selectListPreload(queryWrapper)
                .stream()
                .map(user -> user.setPassword(""))
                .collect(Collectors.toList());
        return users;
    }

    private boolean insertUserRoles(User user) {
        List<UserRole> userRoles = user.getRoleList().stream().map(
                role -> {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(role.getId());
                    userRole.setUserId(user.getId());
                    return userRole;
                }
        ).collect(Collectors.toList());
        userRoleMapper.insertBatchSomeColumn(userRoles);
        return true;
    }

    @Override
    public boolean save(User user) {
        if (user == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        if (StringUtils.isEmpty(user.getUsername())) {
            ExceptionCast.cast(UcenterCode.UCENTER_USERNAME_NONE);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            ExceptionCast.cast(UcenterCode.UCENTER_PASSWORD_NONE);
        }
        //密码加密
        String newPassword = BCryptUtil.encode(user.getPassword());//加密后的密码
        user.setPassword(newPassword);
        this.baseMapper.insert(user);
        this.insertUserRoles(user);
        return true;
    }

    @Override
    public boolean updateById(User user) {
        if (user == null || StringUtils.isEmpty(user.getId())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getId());
        userRoleMapper.delete(wrapper);
        String password = user.getPassword();
        if (!StringUtils.isEmpty(password)) {
            //密码加密
            String newPassword = BCryptUtil.encode(user.getPassword());//加密后的密码
            user.setPassword(newPassword);
        } else {
            user.setPassword(null);
        }
        this.baseMapper.updateById(user);
        this.insertUserRoles(user);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        userRoleMapper.delete(wrapper);
        this.baseMapper.deleteById(id);
        return true;
    }


    public int updatePass(UserPassVo passVo) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        String id = userJwt.getId();
        User user = this.baseMapper.selectById(id);
        String oldPass = passVo.getOldPass();
        String newPass = passVo.getNewPass();

        if (!BCryptUtil.matches(oldPass, user.getPassword())) {
            ExceptionCast.cast(UcenterCode.UCENTER_OLD_PASSWORD_ERROR);
        }
        if (BCryptUtil.matches(newPass, user.getPassword())) {
            ExceptionCast.cast(UcenterCode.UCENTER_NEW_PASSWORD_ERROR);
        }

        String newPassword = BCryptUtil.encode(newPass);//加密后的密码
        User user1 = new User().setId(id).setPassword(newPassword);
        return this.baseMapper.updateById(user1);
    }

    public int updateProfile(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HlOauth2Util hlOauth2Util = new HlOauth2Util();
        HlOauth2Util.UserJwt userJwt = hlOauth2Util.getUserJwtFromHeader(request);
        if (userJwt == null) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        String id = userJwt.getId();
        if (user == null || StringUtils.isEmpty(user.getId())||!user.getId().equals(id)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        user.setUsername(null);
        user.setPassword(null);
        user.setStaffId(null);
        return this.baseMapper.updateById(user);
    }

}
