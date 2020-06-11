package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.haili.framework.domain.basic.JournalingProductionShiftReport;
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
public interface JournalingProductionShiftReportMapper extends BaseMapper<JournalingProductionShiftReport> {
    @Select("<script>" +
            "SELECT * FROM ( " +
            "SELECT " +
            "report.id AS id, " +
            "report.date AS date, " +
            "report.shift_id AS shift_id, " +
            "COUNT(item.product_number) AS produced_coil_number, " +
            "SUM( item.input_weight ) AS total_input_weight, " +
            "SUM( item.output_weight ) AS total_output_weight, " +
            "SUM( item.input_weight ) - SUM( item.output_weight ) AS total_loss_weight, " +
            "SUM( item.output_weight ) / SUM( item.input_weight ) AS output_rate, " +
            "report.capacity_utilization as capacity_utilization, " +
            "report.expected_attendance_num as expected_attendance_num, " +
            "report.actual_attendance_num AS actual_attendance_num, " +
            "report.actual_attendance AS actual_attendance , " +
            "report.shift_leader AS shift_leader, " +
            "report.shift_leader_name AS shift_leader_name, " +
            "report.supervisor AS supervisor, " +
            "report.supervisor_name AS supervisor_name, " +
            "report.inspector AS inspector, " +
            "report.inspector_name AS inspector_name, " +
            "report.matters_record AS matters_record, " +
            "report.shift_handover AS shift_handover, " +
            "report.status AS status, " +
            "report.type AS type, " +
            "report.create_time AS create_time, " +
            "report.update_time AS update_time, " +
            "report.create_person AS create_person, " +
            "report.update_person AS update_person " +
            "FROM tb_journaling_production_shift_report report LEFT JOIN " +
            "<choose> " +
            "<when test='et.type == 0'>" +
            "tb_journaling_rewind_item item " +
            "</when>" +
            "<when test='et.type == 1'>" +
            "tb_journaling_rolling_mill_item item " +
            "</when>" +
            "<when test='et.type == 2'>" +
            "tb_journaling_anneal_item item " +
            "</when>" +
            "<when test='et.type == 3'>" +
            "tb_journaling_finishing_tension_leveler_item item " +
            "</when>" +
            "<otherwise> " +
            "</otherwise> " +
            "</choose> " +
            "ON item.date = report.date AND item.shift_id = report.shift_id " +
            "GROUP BY report.date, report.shift_id, report.type ) r " +
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
            @Result(column = "shift_leader_name", property = "shiftLeaderName"),
            @Result(column = "supervisor", property = "supervisor"),
            @Result(column = "supervisor_name", property = "supervisorName"),
            @Result(column = "inspector", property = "inspector"),
            @Result(column = "inspector_name", property = "inspectorName"),
            @Result(column = "matters_record", property = "mattersRecord"),
            @Result(column = "shift_handover", property = "shiftHandover"),
            @Result(column = "status", property = "status"),
            @Result(column = "type", property = "type"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson")
    })
    IPage<JournalingProductionShiftReport> getPage(IPage<JournalingProductionShiftReport> page, @Param("ew") Wrapper<JournalingProductionShiftReport> queryWrapper,@Param(Constants.ENTITY) JournalingProductionShiftReport entity);

    @Select("<script>" +
            "SELECT * FROM ( " +
            "SELECT " +
            "report.id AS id, " +
            "report.date AS date, " +
            "report.shift_id AS shift_id, " +
            "COUNT(item.product_number) AS produced_coil_number, " +
            "SUM( item.input_weight ) AS total_input_weight, " +
            "SUM( item.output_weight ) AS total_output_weight, " +
            "SUM( item.input_weight ) - SUM( item.output_weight ) AS total_loss_weight, " +
            "SUM( item.output_weight ) / SUM( item.input_weight ) AS output_rate, " +
            "report.capacity_utilization as capacity_utilization, " +
            "report.expected_attendance_num as expected_attendance_num, " +
            "report.actual_attendance_num AS actual_attendance_num, " +
            "report.actual_attendance AS actual_attendance , " +
            "report.shift_leader AS shift_leader, " +
            "report.shift_leader_name AS shift_leader_name, " +
            "report.supervisor AS supervisor, " +
            "report.supervisor_name AS supervisor_name, " +
            "report.inspector AS inspector, " +
            "report.inspector_name AS inspector_name, " +
            "report.matters_record AS matters_record, " +
            "report.shift_handover AS shift_handover, " +
            "report.status AS status, " +
            "report.type AS type, " +
            "report.create_time AS create_time, " +
            "report.update_time AS update_time, " +
            "report.create_person AS create_person, " +
            "report.update_person AS update_person " +
            "FROM tb_journaling_production_shift_report report LEFT JOIN " +
            "<choose> " +
            "<when test='et.type == 0'>" +
            "tb_journaling_rewind_item item " +
            "</when>" +
            "<when test='et.type == 1'>" +
            "tb_journaling_rolling_mill_item item " +
            "</when>" +
            "<when test='et.type == 2'>" +
            "tb_journaling_anneal_item item " +
            "</when>" +
            "<when test='et.type == 3'>" +
            "tb_journaling_finishing_tension_leveler_item item " +
            "</when>" +
            "<otherwise> " +
            "</otherwise> " +
            "</choose> " +
            "ON item.date = report.date AND item.shift_id = report.shift_id " +
            "GROUP BY report.date, report.shift_id, report.type ) r " +
            "${ew.customSqlSegment}" +
            "</script>")
    @ResultMap("reportMap")
    List<JournalingProductionShiftReport> getList(@Param("ew") Wrapper<JournalingProductionShiftReport> queryWrapper,@Param(Constants.ENTITY) JournalingProductionShiftReport entity);


}
