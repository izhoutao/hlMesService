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
        userJwt.setAvatar((String)jwtClaims.get("avatar"));
        userJwt.setDepartment((String)jwtClaims.get("department"));
        userJwt.setRoles((List<String>) jwtClaims.get("roles"));
        userJwt.setMenus((List<String>)jwtClaims.get("menus"));
        return userJwt;
    }

    @Data
    public static class UserJwt {
        private String id;
        private String staffId;
        private String name;
        private String avatar;
        private String department;
        private List<String> roles;
        private List<String> menus;
    }

}
