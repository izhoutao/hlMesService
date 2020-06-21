package com.haili.framework.domain.ucenter.response;

import com.google.common.collect.ImmutableMap;
import com.haili.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * Created by admin on 2018/3/5.
 */
@ToString
public enum UcenterCode implements ResultCode {
    UCENTER_USERNAME_NONE(false,26101,"请输入账号！"),
    UCENTER_PASSWORD_NONE(false,26102,"请输入密码！"),
    UCENTER_VERIFYCODE_NONE(false,26103,"请输入验证码！"),
    UCENTER_ACCOUNT_NOTEXISTS(false,26104,"账号不存在！"),
    UCENTER_CREDENTIAL_ERROR(false,26105,"账号或密码错误！"),
    UCENTER_OLD_PASSWORD_ERROR(false,26106,"修改失败，旧密码错误！"),
    UCENTER_NEW_PASSWORD_ERROR(false,26107,"新密码不能与旧密码相同！"),
    UCENTER_LOGIN_ERROR(false,26108,"登陆过程出现异常请尝试重新操作！");

    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;
    private UcenterCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, UcenterCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, UcenterCode> builder = ImmutableMap.builder();
        for (UcenterCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
