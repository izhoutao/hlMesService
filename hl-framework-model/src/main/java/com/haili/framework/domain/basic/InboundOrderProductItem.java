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
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_inbound_order_product_item")
public class InboundOrderProductItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 钢卷编号
     */
    private String productNumber;

    /**
     * 原料编号
     */
    private String materialNumber;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 表面品级
     */
    private String surfaceFinish;

    /**
     * 规格mm*mm
     */
    private String specification;

    /**
     * 净重(kg)
     */
    private Float netWeight;

    /**
     * 毛重(kg)
     */
    private Float grossWeight;

    /**
     * 参考厚度(mm)
     */
    private Float referenceThickness;

    /**
     * 参考宽度(mm)
     */
    private Float referenceWidth;

    /**
     * 参考长度(mm)
     */
    private Float referenceLength;

    /**
     * 边部
     */
    private String edge;

    /**
     * 等级
     */
    private String grade;

    /**
     * 检验员
     */
    private String inspector;

    /**
     * 条码
     */
    private String barcode;

    /**
     * 日期
     */
    private LocalDate date;

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

    /**
     * 工单号
     */
    private String workOrderNumber;
}
