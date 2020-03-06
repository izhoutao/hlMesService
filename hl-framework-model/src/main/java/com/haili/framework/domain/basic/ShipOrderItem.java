package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_ship_order_item")
public class ShipOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 出货项号
     */
    private String shipOrderItemNumber;

    /**
     * 出货单号
     */
    private String shipOrderId;

    /**
     * 物料id
     */
    private String materialId;

    /**
     * 物料名
     */
    private String materialName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 目标数量
     */
    private Integer requestNum;

    /**
     * 完成数量
     */
    private Integer shippedNum;

    /**
     * 出货项状态：0，未完成，1，已完成
     */
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 所属子订单id
     */
    private String orderItemId;

    /**
     * 所属子订单号
     */
    private String orderItemNumber;


}
