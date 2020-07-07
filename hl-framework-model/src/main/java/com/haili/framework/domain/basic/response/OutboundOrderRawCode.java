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

    WORK_ORDER_IS_NULL(false, 25401, "工单号不能为空！"),
    WORK_ORDER_DOES_NOT_EXIST(false, 25402, "工单不存在！"),
    RAW_MATERIAL_RECEIPT_DOES_NOT_EXIST(false, 25403, "工单发料出库单不存在！"),
    RAW_MATERIAL_DOES_NOT_EXIST(false, 25404, "原料钢卷料不存在！"),
    CANNOT_EDIT_NON_NEWLY_BUILT_OUTBOUND_ORDER_RAW(false, 25405, "无法编辑非开立工单发料出库单！"),
    WORK_ORDER_HAS_RAW_ITEM_OUT(false, 25406, "工单已发料出库，无法修改工艺路线！"),
    CANNOT_CHOOSE_THIS_PRODUCT_NUMBER(false, 25407, "无法选择该钢卷编号！"),
    SPLIT_PARAMETER_ERROR(false, 25408, "分卷参数错误！"),
    CANNOT_UNDO_SPLIT(false, 25409, "无法撤销分卷！"),
    COIL_NOT_BEEN_SPLIT(false, 25410, "尚未对所选钢卷做分卷！"),
    CANNOT_NOT_REMOVE_SPLIT_COIL(false, 25411, "无法删除分卷！"),
    CANNOT_NOT_REMOVE_OUTBOUND_RAE_ITEM(false, 25012, "无法删除工单发料项！"),
    MISMATCH_STEELGRADE_WITH_WORK_ORDER(false, 25013, "所选钢卷与工单钢种不匹配！");
    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;

    private OutboundOrderRawCode(boolean success, int code, String message) {
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
