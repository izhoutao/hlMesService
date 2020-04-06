package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_journaling_rolling_mill_item")
public class JournalingRollingMillItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 报工单id
     */
    private String journalingRollingMillId;

    /**
     * 钢卷编号
     */
    private String productNumber;

    /**
     * 钢种
     */
    private String steelGrade;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 进料厚度(mm)
     */
    private Integer inputThickness;

    /**
     * 进料重量(kg)
     */
    private Integer inputWeight;

    /**
     * 轧延参数-总道次数
     */
    private Integer paramTotalRollingPass;

    /**
     * 轧延参数-总轧下率(%)
     */
    private Integer paramTotalReductionRate;

    /**
     * 出料厚度(mm)
     */
    private Integer outputThickness;

    /**
     * 出料长度(mm)
     */
    private Integer outputLength;

    /**
     * 出料重量(kg)
     */
    private Integer outputWeight;

    /**
     * 班别id
     */
    private String shiftId;

    /**
     * 日期
     */
    @NotNull(message = "报工日期不能为空")
//    @Past(message = "报工日期必须是一个过去的日期")
    private LocalDate date;

    /**
     * 状态：0，新建；1，班长已审核；2，主管已审核; 3，呈阅已审核;
     */
    private Integer status;

    /**
     * 辊号
     */
    private String rollerNumber;

    /**
     * 辊类别
     */
    private String rollerType;

    /**
     * 辊更换原因
     */
    private String rollerReplaceReason;


}
