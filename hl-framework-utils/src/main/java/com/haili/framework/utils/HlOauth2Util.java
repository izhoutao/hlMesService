package com.haili.framework.utils;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by mrt on 2018/5/25.
 */
public class HlOauth2Util {

    public static UserJwt getUserJwtFromHeader(HttpServletRequest request) {
        Map<String, Object> jwtClaims = Oauth2Util.getJwtClaimsFromHeader(request);
        if (jwtClaims == null || StringUtils.isEmpty((String)jwtClaims.get("id"))) {
            return null;
        }
        UserJwt userJwt = new UserJwt();
        userJwt.setId((String) jwtClaims.get("id"));
        userJwt.setStaffId((String)jwtClaims.get("staffId"));
        userJwt.setName((String)jwtClaims.get("name"));
        userJwt.setUsername((String)jwtClaims.get("username"));
        userJwt.setSex((String)jwtClaims.get("sex"));
        userJwt.setAvatar((String)jwtClaims.get("avatar"));
        userJwt.setDepartment((String)jwtClaims.get("department"));
        userJwt.setPhone((String)jwtClaims.get("phone"));
        userJwt.setEmail((String)jwtClaims.get("email"));
        userJwt.setRoles((List<String>) jwtClaims.get("roles"));
        userJwt.setMenus((List<String>)jwtClaims.get("menus"));
        return userJwt;
    }

    @Data
    public static class UserJwt {
        private String id;
        private String staffId;
        private String username;
        private String name;
        private String sex;
        private String avatar;
        private String department;
        private String phone;
        private String email;
        private List<String> roles;
        private List<String> menus;
    }

}
