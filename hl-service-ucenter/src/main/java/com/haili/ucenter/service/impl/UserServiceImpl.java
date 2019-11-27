package com.haili.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.ucenter.User;
import com.haili.framework.domain.ucenter.UserRole;
import com.haili.framework.domain.ucenter.response.UcenterCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.BCryptUtil;
import com.haili.ucenter.mapper.UserMapper;
import com.haili.ucenter.mapper.UserRoleMapper;
import com.haili.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    //根据账号查询用户信息
    public User getUserByUserName(String username) {
        if (username == null || StringUtils.isEmpty(username)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return this.baseMapper.getUserByUserName(username);
    }


    @Override
    public IPage<User> page(IPage<User> page, Wrapper<User> queryWrapper) {
        IPage<User> userIPage = this.baseMapper.selectPage(page, queryWrapper);
        System.out.println(userIPage);
        return super.page(page, queryWrapper);
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
        //密码加密
        String newPassword = BCryptUtil.encode(user.getPassword());//加密后的密码
        user.setPassword(newPassword);
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
}
