package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_journaling_grind_item")
public class JournalingGrindItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 报工单id
     */
    private String journalingGrindId;

    /**
     * 辊号
     */
    private String rollerNumber;

    /**
     * 种类
     */
    private String type;

    /**
     * 上机时间
     */
    private LocalDateTime beginTime;

    /**
     * 磨前外径
     */
    private Float outerDiameterBeforeGrinding;

    /**
     * 磨前情况说明
     */
    private String descriptionBeforeGrinding;

    /**
     * 下机时间
     */
    private LocalDateTime endTime;

    /**
     * 磨后外径
     */
    private Float outerDiameterAfterGrinding;

    /**
     * 磨工
     */
    private String grinder;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 班别id
     */
    private String shiftId;

    /**
     * 日期
     */
    @NotNull(message = "报工日期不能为空")
    private LocalDate date;

    /**
     * 状态：0，新建；1，班长已审核；2，主管已审核; 3，呈阅已审核;
     */
    private Integer status;


}
