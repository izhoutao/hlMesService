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
 * @since 2020-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_work_order")
public class WorkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 工单号
     */
    private String workOrderNumber;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 物料id
     */
    private String materialId;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 密度
     */
    private Double density;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 客户名
     */
    private String customerName;

    /**
     * 用途
     */
    private String uses;

    /**
     * 需求重量
     */
    private Float num;

    /**
     * 计划开始时间
     */
    private LocalDateTime schStartTime;

    /**
     * 计划结束时间
     */
    private LocalDateTime schCloseTime;

    /**
     * 实际结束时间
     */
    private LocalDateTime closeTime;

    /**
     * 工单状态：0、新建，1、已上线，2、已完成
     */
    private Integer status;

    /**
     * 已上线重量
     */
    private Integer onLineNum;

    /**
     * 已完成重量
     */
    private Integer outputNum;

    /**
     * 线别id
     */
    private String lineId;

    /**
     * 工艺路线id
     */
    private String workflowId;

    /**
     * 工艺路线json
     */
    private String jsonTextWorkflow;

    /**
     * 生产要求
     */
    private String requirements;

    /**
     * 目标宽度
     */
    private Double targetWidth;

    /**
     * 宽度容差
     */
    private Double toleranceWidth;

    /**
     * 目标厚度
     */
    private Double targetThickness;

    /**
     * 厚度容差
     */
    private Double toleranceThickness;


}
