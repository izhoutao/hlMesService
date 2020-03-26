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
    private Integer num;
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
     * 生产路线
     */
    private String workflowId;

    /**
     * 线别
     */
    private String lineId;


    private String jsonTextWorkflow;

}
