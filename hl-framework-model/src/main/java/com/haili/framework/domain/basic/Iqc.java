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
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_iqc")
public class Iqc implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String serialNumber;

    /**
     * 日期
     */
    private LocalDate inspectDate;
    /**
     * 是否已登记
     */
    private Boolean isMark;
    private String lineId;
    private String shiftId;
    private String inboundOrderId;
    private String materialId;
    private String vendorId;
    private String materialTypeId;
    private String customerId;
    private String nextOperationId;
    private String surfaceGrade;
    private String uses;
    private String checkList;
    private String defectList;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;




}
