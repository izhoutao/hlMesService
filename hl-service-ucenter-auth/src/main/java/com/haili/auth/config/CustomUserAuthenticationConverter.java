package com.haili.auth.config;

import com.haili.auth.service.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        LinkedHashMap response = new LinkedHashMap();
        String name = authentication.getName();
        response.put("user_name", name);

        Object principal = authentication.getPrincipal();
        UserJwt userJwt = null;
        if(principal instanceof  UserJwt){
            userJwt = (UserJwt) principal;
        }else{
            //refresh_token默认不去调用userdetailService获取用户信息，这里我们手动去调用，得到 UserJwt
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            userJwt = (UserJwt) userDetails;
        }
        response.put("id", userJwt.getId());
        response.put("staffId", userJwt.getStaffId());
        response.put("name", userJwt.getName());
        response.put("username", userJwt.getUsername());
        response.put("avatar",userJwt.getAvatar());
        response.put("department",userJwt.getDepartment());
        response.put("sex",userJwt.getSex());
        response.put("phone",userJwt.getPhone());
        response.put("email",userJwt.getEmail());
        response.put("roles",userJwt.getRoles());
//        response.put("menus",userJwt.getMenus());
        System.out.println("authentication============>" + authentication);

        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }


}
