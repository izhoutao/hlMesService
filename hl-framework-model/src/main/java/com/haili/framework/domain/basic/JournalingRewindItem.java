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
@TableName("tb_journaling_rewind_item")
public class JournalingRewindItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 报工单id
     */
    private String journalingRewindId;

    /**
     * 钢卷编号
     */
    private String productNumber;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 热轧产地
     */
    private String hotRollOrigin;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 进料厚度
     */
    private Float inputThickness;

    /**
     * 进料重量
     */
    private Float inputWeight;

    /**
     * 生产速度
     */
    private Float processVelocity;

    /**
     * 焊机电流
     */
    private Float welderCurrent;

    /**
     * 焊机速度
     */
    private Float welderVelocity;

    /**
     * 上机时间
     */
    private LocalDateTime beginTime;

    /**
     * 下机时间
     */
    private LocalDateTime endTime;

    /**
     * 出料长度
     */
    private Float outputLength;

    /**
     * 出料重量
     */
    private Float outputWeight;

    /**
     * 损耗原因
     */
    private String lossReason;

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
