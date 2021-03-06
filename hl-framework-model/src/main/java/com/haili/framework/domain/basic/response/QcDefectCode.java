package com.haili.framework.domain.basic.response;

import com.google.common.collect.ImmutableMap;
import com.haili.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * Created by admin on 2018/3/5.
 */
@ToString
public enum QcDefectCode implements ResultCode {

    QC_DEFECT_IPQC_NOT_EXIST(false,25501,"质检单不存在，请先保存创建！"),    //操作代码
    QC_DEFECT_IPQC_CANNOT_CHANGE(false,25502,"无法变更所属质检单！");    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;
    private QcDefectCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, QcDefectCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, QcDefectCode> builder = ImmutableMap.builder();
        for (QcDefectCode commonCode : values()) {
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
