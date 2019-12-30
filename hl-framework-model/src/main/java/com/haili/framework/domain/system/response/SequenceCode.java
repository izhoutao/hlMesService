package com.haili.framework.domain.system.response;

import com.google.common.collect.ImmutableMap;
import com.haili.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * Created by admin on 2018/3/5.
 */
@ToString
public enum SequenceCode implements ResultCode {

    SEQUENCE_CANNOT_DETERMINE_BIZ_NAME(false, 23001, "无法确定编码序列所关联业务！"),
    SEQUENCE_CANNOT_DETERMINE_CODE_RULE(false, 23002, "无法确定编码规则！"),
    SEQUENCE_CANNOT_RESOLVE_MIN_OR_MAX(false, 23003, "无法解析序列最小值或最大值！"),
    SEQUENCE_CANNOT_RESOLVE_FIRST_DAY_OF_WEEK(false, 23004, "无法解析星期首日！"),
    SEQUENCE_MAXIMUM_EXCEEDED(false, 23005, "超出序列最大值！"),
    SEQUENCE_FIRST_DAY_OF_WEEK_NOT_DETERMINED(false, 23006, "未确定星期首日！");


    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;

    private SequenceCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private static final ImmutableMap<Integer, SequenceCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, SequenceCode> builder = ImmutableMap.builder();
        for (SequenceCode commonCode : values()) {
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
