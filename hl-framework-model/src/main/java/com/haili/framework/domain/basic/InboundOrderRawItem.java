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
 * @since 2020-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_inbound_order_raw_item")
public class InboundOrderRawItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 收货时间
     */
    private LocalDateTime receivingTime;

    /**
     * 原料编号
     */
    private String materialNumber;

    /**
     * 我司（钢卷）编号
     */
    private String productNumber;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 产地id(供应商id)
     */
    private String vendorId;

    /**
     * 产地名(供应商名)
     */
    private String vendorName;

    /**
     * 厚度(mm)
     */
    private Float thickness;

    /**
     * 宽度(mm)
     */
    private Float width;

    /**
     * 毛重(kg)
     */
    private Float grossWeight;

    /**
     * 净重(kg)
     */
    private Float netWeight;

    /**
     * 复磅毛重(kg)
     */
    private Float grossWeight2;

    /**
     * 包装重量(kg)
     */
    private Float packageWeight;

    /**
     * 合同号
     */
    private String contractNumber;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 客户名
     */
        private String customerName;

    /**
     * 下单时间
     */
    private LocalDateTime orderTime;

    /**
     * 订单厚度
     */
    private Float orderThickness;

    /**
     * 轧延厚度
     */
    private Float rollingThickness;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;


}
