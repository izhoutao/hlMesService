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
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_outbound_order_raw_item")
public class OutboundOrderRawItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String outboundOrderRawId;

    private String outboundOrderRawDetailId;

    /**
     * 工单号码
     */
    private String workOrderNumber;

    /**
     * 来料编号
     */
    private String materialNumber;

    /**
     * 钢卷编号
     */
    private String productNumber;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 表面品级
     */
    private String surfaceFinish;

    /**
     * 宽度(mm)
     */
    private Float width;

    /**
     * 厚度(mm)
     */
    private Float thickness;

    /**
     * 长度(mm)
     */
    private Float length;

    /**
     * 标签规格mm*mm
     */
    private String labelSpecification;

    /**
     * 实际规格mm*mm
     */
    private String specification;

    /**
     * 标签净重(kg)
     */
    private Float labelNetWeight;

    /**
     * 标签毛重(kg)
     */
    private Float labelGrossWeight;

    /**
     * 实磅净重(kg)
     */
    private Float netWeight;

    /**
     * 实磅毛重(kg)
     */
    private Float grossWeight;

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

//    /**
//     * 条码
//     */
//    private String barcode;

    /**
     * 出库时间
     */
    private LocalDateTime time;

    /**
     * 备注
     */
    private String description;

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

    private String jsonTextWorkflow;
    private String currentOperationLabel;
    private String nextOperationLabel;
    private Integer nextOperationStatus;
    private String operationHistory;

    /**
     * 状态：0，有效；1无效
     */
    private Integer status;
    /**
     * 父钢卷id
     */
    private String parentId;

}
