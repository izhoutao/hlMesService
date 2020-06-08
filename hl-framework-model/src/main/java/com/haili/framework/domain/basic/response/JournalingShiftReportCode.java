package com.haili.framework.domain.basic.response;

import com.google.common.collect.ImmutableMap;
import com.haili.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * Created by admin on 2018/3/5.
 */
@ToString
public enum JournalingShiftReportCode implements ResultCode {

    PRODUCTION_SHIFT_REPORT_ALREADY_APPROVED(false, 25201, "班报表已被审批！"),
    JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_MODIFY(false, 25202, "无法修改已被审批报工记录！"),
    JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_DELETE(false, 25203, "无法删除已被审批报工记录！");

    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;

    private JournalingShiftReportCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private static final ImmutableMap<Integer, JournalingShiftReportCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, JournalingShiftReportCode> builder = ImmutableMap.builder();
        for (JournalingShiftReportCode commonCode : values()) {
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
