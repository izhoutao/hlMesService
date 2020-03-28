package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.framework.domain.basic.JournalingRewindReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
 */
public interface JournalingRewindReportMapper extends BaseMapper<JournalingRewindReport> {
    @Select("<script>" +
            "SELECT * FROM ( " +
            "SELECT " +
            "report.id AS id, " +
            "report.date AS date, " +
            "report.shift_id AS shift_id, " +
            "COUNT(*) AS produced_coil_number, " +
            "SUM( item.input_weight ) AS total_input_weight, " +
            "SUM( item.output_weight ) AS total_output_weight, " +
            "SUM( item.input_weight ) - SUM( item.output_weight ) AS total_loss_weight, " +
            "SUM( item.output_weight ) / SUM( item.input_weight ) AS output_rate, " +
            "report.capacity_utilization as capacity_utilization, " +
            "report.expected_attendance_num as expected_attendance_num, " +
            "COUNT( DISTINCT item.create_person ) AS actual_attendance_num, " +
            "GROUP_CONCAT( DISTINCT item.create_person ) AS actual_attendance , " +
            "report.shift_leader AS shift_leader, " +
            "report.supervisor AS supervisor, " +
            "report.inspector AS inspector, " +
            "report.matters_record AS matters_record, " +
            "report.shift_handover AS shift_handover, " +
            "report.status AS status, " +
            "report.create_time AS create_time, " +
            "report.update_time AS update_time, " +
            "report.create_person AS create_person, " +
            "report.update_person AS update_person " +
            "FROM tb_journaling_rewind_report report, tb_journaling_rewind_item item  " +
            "WHERE item.date = report.date AND item.shift_id = report.shift_id  " +
            "GROUP BY item.date, item.shift_id) r " +
            "${ew.customSqlSegment}" +
            "</script>")
    @Results(id = "reportMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "date", property = "date"),
            @Result(column = "shift_id", property = "shiftId"),
            @Result(column = "produced_coil_number", property = "producedCoilNumber"),
            @Result(column = "total_input_weight", property = "totalInputWeight"),
            @Result(column = "total_output_weight", property = "totalOutputWeight"),
            @Result(column = "total_loss_weight", property = "totalLossWeight"),
            @Result(column = "output_rate", property = "outputRate"),
            @Result(column = "capacity_utilization", property = "capacityUtilization"),
            @Result(column = "expected_attendance_num", property = "expectedAttendanceNum"),
            @Result(column = "actual_attendance_num", property = "actualAttendanceNum"),
            @Result(column = "actual_attendance", property = "actualAttendance"),
            @Result(column = "shift_leader", property = "shiftLeader"),
            @Result(column = "supervisor", property = "supervisor"),
            @Result(column = "inspector", property = "inspector"),
            @Result(column = "matters_record", property = "mattersRecord"),
            @Result(column = "shift_handover", property = "shiftHandover"),
            @Result(column = "status", property = "status"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson")
    })
    IPage<JournalingRewindReport> getPage(IPage<JournalingRewindReport> page, @Param("ew") Wrapper<JournalingRewindReport> queryWrapper);

    @Select("<script>" +
            "SELECT * FROM ( " +
            "SELECT " +
            "report.id AS id, " +
            "report.date AS date, " +
            "report.shift_id AS shift_id, " +
            "COUNT(*) AS produced_coil_number, " +
            "SUM( item.input_weight ) AS total_input_weight, " +
            "SUM( item.output_weight ) AS total_output_weight, " +
            "SUM( item.input_weight ) - SUM( item.output_weight ) AS total_loss_weight, " +
            "SUM( item.output_weight ) / SUM( item.input_weight ) AS output_rate, " +
            "report.capacity_utilization as capacity_utilization, " +
            "report.expected_attendance_num as expected_attendance_num, " +
            "COUNT( DISTINCT item.create_person ) AS actual_attendance_num, " +
            "GROUP_CONCAT( DISTINCT item.create_person ) AS actual_attendance , " +
            "report.shift_leader AS shift_leader, " +
            "report.supervisor AS supervisor, " +
            "report.inspector AS inspector, " +
            "report.matters_record AS matters_record, " +
            "report.shift_handover AS shift_handover, " +
            "report.status AS status, " +
            "report.create_time AS create_time, " +
            "report.update_time AS update_time, " +
            "report.create_person AS create_person, " +
            "report.update_person AS update_person " +
            "FROM tb_journaling_rewind_report report, tb_journaling_rewind_item item  " +
            "WHERE item.date = report.date AND item.shift_id = report.shift_id  " +
            "GROUP BY item.date, item.shift_id) r " +
            "${ew.customSqlSegment}" +
            "</script>")
    @ResultMap("reportMap")
    List<JournalingRewindReport> getList(@Param("ew") Wrapper<JournalingRewindReport> queryWrapper);


}
