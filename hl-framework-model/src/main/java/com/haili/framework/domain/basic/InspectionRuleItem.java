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
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_inspection_rule_item")
public class InspectionRuleItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Integer sequenceNumber;

    private String description;

    private String tool;

    private String frequency;

    private String method;

    private Long specification;

    private String specificationUnit;

    private Long minValue;

    private Long maxValue;

    private String inspectionRuleId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    private String checkType;


}
