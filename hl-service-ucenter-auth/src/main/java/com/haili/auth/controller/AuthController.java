package com.haili.auth.controller;

import com.haili.api.auth.AuthControllerApi;
import com.haili.auth.client.UserClient;
import com.haili.auth.service.AuthService;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.ext.AuthToken;
import com.haili.framework.domain.ucenter.request.LoginRequest;
import com.haili.framework.domain.ucenter.response.AuthCode;
import com.haili.framework.domain.ucenter.response.JwtResult;
import com.haili.framework.domain.ucenter.response.LoginResult;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.QueryResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerApi {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;
    @Autowired
    UserClient userClient;

    @Override
    @PostMapping("/userlogin")
    public LoginResult login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())) {
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if (loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())) {
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //账号
        String username = loginRequest.getUsername();
        //密码
        String password = loginRequest.getPassword();

        //申请令牌
        AuthToken authToken = authService.login(username, password, clientId, clientSecret);

        //用户身份令牌
        String access_token = authToken.getAccess_token();
//        //将令牌存储到cookie
        this.saveCookie(access_token);

        return new LoginResult(CommonCode.SUCCESS, access_token);
    }

    //将令牌存储到cookie
    private void saveCookie(String token) {

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false);

    }

    //退出
    @Override
    @PostMapping("/userlogout")
    public ResponseResult logout() {
        //取出身份令牌
        String uid = getTokenFormCookie();
        //删除redis中token
        authService.delToken(uid);
//        //清除cookie
        clearCookie(uid);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    //清除cookie
    private void clearCookie(String token) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, 0, false);
    }

    @Override
    @GetMapping("/userjwt")
    public JwtResult userjwt() {
        //获取cookie中的令牌
        String access_token = getTokenFormCookie();
        //根据令牌从redis查询jwt
        AuthToken authToken = authService.getUserToken(access_token);
        if (authToken == null) {
            return new JwtResult(CommonCode.FAIL, null);
        }
        return new JwtResult(CommonCode.SUCCESS, authToken.getJwt_token());
    }

    //从cookie中读取访问令牌
    private String getTokenFormCookie() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> cookieMap = CookieUtil.readCookie(request, "uid");
        String access_token = cookieMap.get("uid");
        return access_token;
    }

    @GetMapping("/routes")
    public QueryResponseResult<Menu> getRoutes() {
        Map<String, Object> map = new HashMap<>();
        map.put("lazyLoad", false);
        QueryResponseResult<Menu> responseResult = userClient.getMenuList(map);
        List<Menu> menuList = responseResult.getQueryResult().getList();
        if (menuList == null) {
            return new QueryResponseResult(CommonCode.FAIL, null);
        }
        QueryResult queryResult = new QueryResult();
        queryResult.setList(menuList);
        QueryResponseResult<Menu> queryResponseResult = new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    //刷新令牌
    @GetMapping("/refreshtoken")
    public ResponseResult refreshToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //取出头信息
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization) || authorization.indexOf("Bearer") < 0) {
            return null;
        }
        //从Bearer 后边开始取出token
        String jwt_token = authorization.substring(7);
        //取出身份令牌
        String uid = getTokenFormCookie();
        AuthToken userToken = authService.getUserToken(uid);
        String access_token = userToken.getJwt_token();
        if (StringUtils.isEmpty(jwt_token) || !StringUtils.equals(jwt_token, access_token)) {
            ExceptionCast.cast(AuthCode.AUTH_REFRESH_TOKEN_FAIL);
        }
        String refresh_token = userToken.getRefresh_token();

        //申请令牌
        AuthToken authToken = authService.refresh(refresh_token, clientId, clientSecret);
        //删除redis中token
        authService.delToken(uid);
//        //清除cookie
        clearCookie(uid);
        //用户身份令牌
        access_token = authToken.getAccess_token();
//        //将令牌存储到cookie
        this.saveCookie(access_token);

        return new LoginResult(CommonCode.SUCCESS, access_token);

    }
}
