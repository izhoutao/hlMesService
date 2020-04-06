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
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_journaling_finishing_tension_leveler_item")
public class JournalingFinishingTensionLevelerItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 报工单id
     */
    private String journalingFinishingTensionLevelerId;

    /**
     * 钢卷编号
     */
    private String productNumber;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 表面品级
     */
    private String surfaceFinish;

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
    private Float inputThickness;

    /**
     * 进料宽度(mm)
     */
    private Float inputWidth;

    /**
     * 进料重量(kg)
     */
    private Float inputWeight;

    /**
     * 生产方式：0，精整；1，拉矫
     */
    private Integer paramProductionMode;

    /**
     * 入口张力(KN)
     */
    private Float paramInletTesion;

    /**
     * 总轧制力(T)
     */
    private Float paramTotalRollingForce;

    /**
     * 出口张力(KN)
     */
    private Float paramOutletTesion;

    /**
     * 延伸率(%)
     */
    private Float paramPercentageElongation;

    /**
     * 出料厚度(mm)
     */
    private Float outputThickness;

    /**
     * 出料长度(mm)
     */
    private Float outputLength;

    /**
     * 出料重量(kg)
     */
    private Float outputWeight;

    /**
     * 出料重量损耗(kg)
     */
    private Float outputWeightLoss;

    /**
     * 套筒重量(kg)
     */
    private Float outputSleeveWeight;

    /**
     * 出料速度(m/min)
     */
    private Float outputSpeed;

    /**
     * 上机时间
     */
    private LocalDateTime beginTime;

    /**
     * 下机时间
     */
    private LocalDateTime endTime;

    /**
     * 用时(min)
     */
    private Long costTime;

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


}
