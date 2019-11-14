package com.haili.auth.service;

import com.haili.auth.client.UserClient;
import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.domain.ucenter.ext.UserExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserClient userClient;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if (clientDetails != null) {
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //远程调用用户中心根据账号查询用户信息
        UserExt userext = userClient.findUserExtByUserName(username).getUserExt();
        if (userext == null) {
            //返回空给spring security表示用户不存在
            return null;
        }
        //        XcUserExt userext = new XcUserExt();
        //        userext.setUsername("itcast");
        //        userext.setPassword(new BCryptPasswordEncoder().encode("123"));
        //userext.setPermissions(new ArrayList<XcMenu>()); //权限暂时用静态的

        //取出正确密码（hash值）
        String password = userext.getPassword();
        //这里暂时使用静态密码
        //       String password ="123";
        //用户权限，这里暂时使用静态数据，最终会从数据库读取
        //从数据库获取权限
        List<Role> roles = userext.getRoleList();
        if (roles == null) {
            roles = new ArrayList<>();
        }
        System.out.println("roles=========>" + roles);
        List<String> userRoles = new ArrayList<>();
        roles.forEach(item -> userRoles.add(item.getRoleCode()));
        //使用静态的权限表示用户所拥有的权限
        //        user_permission.add("course_get_baseinfo"); //查询课程信息
        //        user_permission.add("course_pic_list"); //图片查询
        String user_role_string = StringUtils.join(userRoles.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_role_string));
        userDetails.setId(userext.getId());
        userDetails.setStaffId(userext.getStaffId());
        userDetails.setName(userext.getName()); //用户名称
        userDetails.setAvatar(userext.getAvatar()); //用户头像
        userDetails.setDepartment(userext.getDepartment()); //用户类型
       /* UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));*/
        //AuthorityUtils.createAuthorityList("course_get_baseinfo","course_get_list"));
        return userDetails;
    }
}