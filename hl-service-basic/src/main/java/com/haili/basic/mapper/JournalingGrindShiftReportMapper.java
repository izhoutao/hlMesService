package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.haili.framework.domain.basic.JournalingGrindShiftReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-08
 */
public interface JournalingGrindShiftReportMapper extends BaseMapper<JournalingGrindShiftReport> {
    @Select("<script>" +
            "SELECT * FROM ( " +
            "SELECT " +
            "report.id AS id, " +
            "report.date AS date, " +
            "report.shift_id AS shift_id, " +
            "COUNT(item.roller_number) AS grind_roll_number, " +
            "report.total_grind_amount AS total_grind_amount, " +
            "report.polished_roller_number AS polished_roller_number, " +
            "report.abnormal_roller_number AS abnormal_roller_number, " +
            "report.assembled_roll_number AS assembled_roll_number, " +
            "report.expected_attendance_num as expected_attendance_num, " +
            "report.actual_attendance_num as actual_attendance_num, " +
            "report.actual_attendance_staff_ids as actual_attendance_staff_ids, " +
            "report.actual_attendance_names as actual_attendance_names, " +
            "report.shift_leader AS shift_leader, " +
            "report.shift_leader_name AS shift_leader_name, " +
            "report.supervisor AS supervisor, " +
            "report.supervisor_name AS supervisor_name, " +
            "report.inspector AS inspector, " +
            "report.inspector_name AS inspector_name, " +
            "report.matters_record AS matters_record, " +
            "report.shift_handover AS shift_handover, " +
            "report.status AS status, " +
            "report.create_time AS create_time, " +
            "report.update_time AS update_time, " +
            "report.create_person AS create_person, " +
            "report.update_person AS update_person " +
            "FROM tb_journaling_grind_shift_report report LEFT JOIN " +
            "tb_journaling_grind_item item " +
            "ON item.date = report.date AND item.shift_id = report.shift_id " +
            "GROUP BY report.date, report.shift_id ) r " +
            "${ew.customSqlSegment}" +
            "</script>")
    @Results(id = "grindReportMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "date", property = "date"),
            @Result(column = "shift_id", property = "shiftId"),
            @Result(column = "grind_roll_number", property = "grindRollNumber"),
            @Result(column = "total_grind_amount", property = "totalGrindAmount"),
            @Result(column = "polished_roller_number", property = "polishedRollerNumber"),
            @Result(column = "abnormal_roller_number", property = "abnormalRollerNumber"),
            @Result(column = "assembled_roll_number", property = "assembledRollNumber"),
            @Result(column = "expected_attendance_num", property = "expectedAttendanceNum"),
            @Result(column = "actual_attendance_num", property = "actualAttendanceNum"),
            @Result(column = "actual_attendance_staff_ids", property = "actualAttendanceStaffIds"),
            @Result(column = "actual_attendance_names", property = "actualAttendanceNames"),
            @Result(column = "shift_leader", property = "shiftLeader"),
            @Result(column = "supervisor", property = "supervisor"),
            @Result(column = "inspector", property = "inspector"),
            @Result(column = "matters_record", property = "mattersRecord"),
            @Result(column = "shift_handover", property = "shiftHandover"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson"),
            @Result(column = "status", property = "status"),
            @Result(column = "shift_leader_name", property = "shiftLeaderName"),
            @Result(column = "supervisor_name", property = "supervisorName"),
            @Result(column = "inspector_name", property = "inspectorName"),
    })
    IPage<JournalingGrindShiftReport> getPage(IPage<JournalingGrindShiftReport> page, @Param("ew") Wrapper<JournalingGrindShiftReport> queryWrapper, @Param(Constants.ENTITY) JournalingGrindShiftReport entity);

    @Select("<script>" +
            "SELECT * FROM ( " +
            "SELECT " +
            "report.id AS id, " +
            "report.date AS date, " +
            "report.shift_id AS shift_id, " +
            "COUNT(item.roller_number) AS grind_roll_number, " +
            "report.total_grind_amount AS total_grind_amount, " +
            "report.polished_roller_number AS polished_roller_number, " +
            "report.abnormal_roller_number AS abnormal_roller_number, " +
            "report.assembled_roll_number AS assembled_roll_number, " +
            "report.expected_attendance_num as expected_attendance_num, " +
            "report.actual_attendance_num as actual_attendance_num, " +
            "report.actual_attendance_staff_ids as actual_attendance_staff_ids, " +
            "report.actual_attendance_names as actual_attendance_names, " +
            "report.shift_leader AS shift_leader, " +
            "report.shift_leader_name AS shift_leader_name, " +
            "report.supervisor AS supervisor, " +
            "report.supervisor_name AS supervisor_name, " +
            "report.inspector AS inspector, " +
            "report.inspector_name AS inspector_name, " +
            "report.matters_record AS matters_record, " +
            "report.shift_handover AS shift_handover, " +
            "report.status AS status, " +
            "report.create_time AS create_time, " +
            "report.update_time AS update_time, " +
            "report.create_person AS create_person, " +
            "report.update_person AS update_person " +
            "FROM tb_journaling_grind_shift_report report LEFT JOIN " +
            "tb_journaling_grind_item item " +
            "ON item.date = report.date AND item.shift_id = report.shift_id " +
            "GROUP BY report.date, report.shift_id ) r " +
            "${ew.customSqlSegment}" +
            "</script>")
    @ResultMap("grindReportMap")
    List<JournalingGrindShiftReport> getList(@Param("ew") Wrapper<JournalingGrindShiftReport> queryWrapper, @Param(Constants.ENTITY) JournalingGrindShiftReport entity);

}
