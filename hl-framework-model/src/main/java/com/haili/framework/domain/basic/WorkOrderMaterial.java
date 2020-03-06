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
@TableName("tb_work_order_material")
public class WorkOrderMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 工单id
     */
    private String workOrderId;

    /**
     * 物料id
     */
    private String materialId;

    /**
     * 料号
     */
    private String materialCode;

    /**
     * 物料名
     */
    private String materialName;

    /**
     * 规格
     */
    private String materialSpecification;

    /**
     * 需求数量
     */
    private Integer requestNum;

    /**
     * 已使用数量
     */
    private Integer usedNum;

    /**
     * 单位
     */
    private String unit;

    /**
     * 领料状态：0、待领料，1、已领料
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


}
