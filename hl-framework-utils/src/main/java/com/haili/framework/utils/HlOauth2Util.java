package com.haili.framework.utils;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by mrt on 2018/5/25.
 */
public class HlOauth2Util {

    public UserJwt getUserJwtFromHeader(HttpServletRequest request){
        Map<String, String> jwtClaims = Oauth2Util.getJwtClaimsFromHeader(request);
        if(jwtClaims == null || StringUtils.isEmpty(jwtClaims.get("id"))){
            return null;
        }
        UserJwt userJwt = new UserJwt();
        userJwt.setId(jwtClaims.get("id"));
        userJwt.setStaffId(jwtClaims.get("staffId"));
        userJwt.setName(jwtClaims.get("name"));
        userJwt.setAvatar(jwtClaims.get("avatar"));
        userJwt.setDepartment(jwtClaims.get("department"));
        return userJwt;
    }

    @Data
    public class UserJwt{
        private String id;
        private String staffId;
        private String name;
        private String avatar;
        private String department;
    }

}
