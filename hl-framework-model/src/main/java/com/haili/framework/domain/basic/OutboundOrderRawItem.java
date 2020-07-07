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
 * @since 2020-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_outbound_order_raw_item")
public class OutboundOrderRawItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 工单号码
     */
    private String workOrderNumber;

    /**
     * 原料编号
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
     * 发料出库时间
     */
    private LocalDateTime time;

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

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 路由
     */
    private String jsonTextWorkflow;

    /**
     * 当前工艺名称
     */
    private String currentOperationLabel;

    /**
     * 下一工艺名称
     */
    private String nextOperationLabel;

    /**
     * 下一工艺状态：0，未锁定；1，已锁定
     */
    private Integer nextOperationStatus;

    /**
     * 工艺历史
     */
    private String operationHistory;

    /**
     * 状态：0，有效；1，无效; 2，子卷尚未生效
     */
    private Integer status;

    /**
     * 父钢卷id
     */
    private String parentId;

    /**
     * 计划结束时间
     */
    private LocalDateTime schCloseTime;

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


}
