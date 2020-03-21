package com.haili.framework.domain.basic.response;

import com.google.common.collect.ImmutableMap;
import com.haili.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * Created by admin on 2018/3/5.
 */
@ToString
public enum OutboundOrderRawCode implements ResultCode {

    WORK_ORDER_IS_NULL(false,25001,"工单号不能为空！"),
    WORK_ORDER_DOES_NOT_EXIST(false,25002,"工单不存在！"),
    RAW_MATERIAL_RECEIPT_DOES_NOT_EXIST(false,25003,"工单发料出库单不存在！"),
    RAW_MATERIAL_DOES_NOT_EXIST(false,25004,"原料钢卷料不存在！");
    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;
    private OutboundOrderRawCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, OutboundOrderRawCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, OutboundOrderRawCode> builder = ImmutableMap.builder();
        for (OutboundOrderRawCode commonCode : values()) {
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
