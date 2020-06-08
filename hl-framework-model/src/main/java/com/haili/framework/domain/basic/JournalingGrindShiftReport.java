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
@TableName("tb_journaling_grind_shift_report")
public class JournalingGrindShiftReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 日期
     */
    @NotNull(message = "日期不能为空")
    private LocalDate date;

    /**
     * 班别
     */
    @NotNull(message = "班别不能为空")
    private String shiftId;

    /**
     * 研磨轧辊数
     */
    private Integer grindRollNumber;

    /**
     * 总研磨量mm
     */
    private Float totalGrindAmount;

    /**
     * 抛光辊数量
     */
    private Integer polishedRollerNumber;

    /**
     * 异常辊数量
     */
    private Integer abnormalRollerNumber;

    /**
     * 组装轧辊数量
     */
    private Integer assembledRollNumber;

    /**
     * 应到人数
     */
    private Integer expectedAttendanceNum;

    /**
     * 实到人数
     */
    private Integer actualAttendanceNum;

    /**
     * 出勤工号
     */
    private String actualAttendanceStaffIds;

    /**
     * 出勤姓名
     */
    private String actualAttendanceNames;

    /**
     * 班长
     */
    private String shiftLeader;

    /**
     * 主管
     */
    private String supervisor;

    /**
     * 呈阅
     */
    private String inspector;

    /**
     * 问题记录
     */
    private String mattersRecord;

    /**
     * 交接班事宜
     */
    private String shiftHandover;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 状态：0，新建；1，班长已审核；2，主管已审核; 3，呈阅已审核;
     */
    private Integer status;

    /**
     * 班长姓名
     */
    private String shiftLeaderName;

    /**
     * 主管姓名
     */
    private String supervisorName;

    /**
     * 呈阅姓名
     */
    private String inspectorName;


}
