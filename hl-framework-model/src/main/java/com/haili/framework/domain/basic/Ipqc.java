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
 * @since 2019-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_ipqc")
public class Ipqc implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 序列号
     */
    private String serialNumber;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    private String inspector;

    /**
     * 检验日期
     */
    private LocalDate inspectDate;

    /**
     * 是否已提交
     */
    private Boolean isMark;

    /**
     * 线别
     */
    private String lineId;

    /**
     * 班别
     */
    private String shiftId;

    /**
     * 料号
     */
    private String materialId;
    @TableField(exist = false)
    private String materialCode;
    @TableField(exist = false)
    private String materialName;
    @TableField(exist = false)
    private String materialTypeName;

    private String materialTypeId;

    private String customerId;

    private String nextOperationId;

    private String surfaceGrade;

    private String uses;

    private String checkList;

    private String defectList;


}
