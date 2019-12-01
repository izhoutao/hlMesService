package com.haili.auth.service;

import com.haili.auth.client.UserClient;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.Role;
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
import java.util.Set;
import java.util.stream.Collectors;

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
        com.haili.framework.domain.ucenter.User user = userClient.getUserByUserName(username).getUser();
        if (user == null) {
            //返回空给spring security表示用户不存在
            return null;
        }
        //取出正确密码（hash值）
        String password = user.getPassword();

        //从数据库获取权限
        List<Role> roles = user.getRoleList();
        if (roles == null) {
            roles = new ArrayList<>();
        }
        System.out.println("roles=========>" + roles);
        List<String> authorityList = new ArrayList<>();
        Set<String> menus = roles.stream().flatMap(role -> role.getMenuList().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getCode()))
                .map(Menu::getCode).collect(Collectors.toSet());
        menus.forEach(item -> authorityList.add(item));

        //使用静态的权限表示用户所拥有的权限
        //        user_permission.add("course_get_baseinfo"); //查询课程信息
        //        user_permission.add("course_pic_list"); //图片查询
        String user_authrity_string = StringUtils.join(authorityList.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_authrity_string));
        userDetails.setId(user.getId());
        userDetails.setStaffId(user.getStaffId());
        userDetails.setName(user.getName()); //用户名称`
        userDetails.setAvatar(user.getAvatar()); //用户头像

        userDetails.setDepartment(user.getDepartment()); //部门
        userDetails.setRoles(roles.stream().map(Role::getCode).collect(Collectors.toList()));
       /* UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));*/
        //AuthorityUtils.createAuthorityList("course_get_baseinfo","course_get_list"));
        return userDetails;
    }
}
