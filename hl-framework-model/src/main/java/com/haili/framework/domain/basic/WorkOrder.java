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
 * @since 2020-02-27
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
     * 物料名称
     */
    private String materialName;

    /**
     * 需求数量
     */
    private Float num;
    /**
     * 已上线数量
     */
    private Integer onLineNum;
    /**
     * 已完成数量
     */
    private Integer outputNum;

    /**
     * 工单状态：0、新建，1、已上线，2、进行中，3、已关闭
     */
    private Integer status;

    /**
     * 计划开始时间
     */
    private LocalDateTime schStartTime;

    /**
     * 计划结束时间
     */
    private LocalDateTime schCloseTime;

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
    private String targetWidth;
    /**
     * 宽度容差
     */
    private String toleranceWidth;
    /**
     * 目标厚度
     */
    private String targetThickness;
    /**
     * 厚度容差
     */
    private String toleranceThickness;

}
