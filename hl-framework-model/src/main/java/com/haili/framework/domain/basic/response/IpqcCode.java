package com.haili.framework.domain.basic.response;

import com.google.common.collect.ImmutableMap;
import com.haili.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * Created by admin on 2018/3/5.
 */
@ToString
public enum IpqcCode implements ResultCode {

    COIL_CURRENT_PROCESS_CANNOT_BE_MODIFIED(false,25001,"不能修改钢卷当前制程！"),
    WORK_ORDER_DOES_NOT_EXIST(false,25002,"工单不存在！"),
    RAW_MATERIAL_RECEIPT_DOES_NOT_EXIST(false,25003,"工单发料出库单不存在！"),
    RAW_MATERIAL_DOES_NOT_EXIST(false,25004,"原料钢卷料不存在！"),
    CANNOT_EDIT_NON_NEWLY_BUILT_OUTBOUND_ORDER_RAW(false,25004,"无法编辑非开立工单发料出库单！"),
    WORK_ORDER_HAS_RAW_ITEM_OUT(false,25004,"工单已发料出库，无法修改工艺路线！");
    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;
    private IpqcCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, IpqcCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, IpqcCode> builder = ImmutableMap.builder();
        for (IpqcCode commonCode : values()) {
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
