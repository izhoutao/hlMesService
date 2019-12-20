package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 客户名
     */
    private String customerName;

    /**
     * 订单状态：1、已创建，2、生产中，3、已完成
     */
    private Integer status;

    /**
     * 订单描述
     */
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 交付日期
     */
    private LocalDate deliveryDate;

    /**
     * 开始日期
     */
    private LocalDate beginDate;

    /**
     * 完成日期
     */
    private LocalDate finishDate;


}
