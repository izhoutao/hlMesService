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
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_journaling_production_shift_report")
public class JournalingProductionShiftReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @NotNull(message = "日期不能为空")
    private LocalDate date;
    @NotNull(message = "班别不能为空")
    private String shiftId;

    private Integer producedCoilNumber;

    private Float totalInputWeight;

    private Float totalLossWeight;

    private Float totalOutputWeight;

    private Float outputRate;

    private Float capacityUtilization;
    /**
     * 应到人数
     */
    private Integer expectedAttendanceNum;
    /**
     * 实到人数
     */
    private Integer actualAttendanceNum;
    /**
     * 实际出勤人员
     */
    private String actualAttendance;

    private String shiftLeader;
    private String shiftLeaderName;

    private String supervisor;
    private String supervisorName;

    private String inspector;
    private String inspectorName;

    private String mattersRecord;

    private String shiftHandover;

    private Integer status;

    private Integer type;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;


}
