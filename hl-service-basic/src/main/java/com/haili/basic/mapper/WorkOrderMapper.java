package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.framework.domain.basic.WorkOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-02-27
 */
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {

    @Select("<script>" +
            "SELECT * FROM ("+
            "SELECT " +
            "w.*, " +
            "SUM( iopr.gross_weight ) AS output_num " +
            "FROM " +
            "( " +
            "SELECT " +
            "wo.id, " +
            "wo.work_order_number, " +
            "wo.create_time, " +
            "wo.update_time, " +
            "wo.create_person, " +
            "wo.update_person, " +
            "wo.material_id, " +
            "wo.material_name, " +
            "wo.num, " +
            "wo.sch_start_time, " +
            "wo.sch_close_time, " +
            "wo.`status`, " +
            "wo.line_id, " +
            "wo.workflow_id, " +
            "wo.json_text_workflow, " +
            "wo.requirements, " +
            "wo.target_width, " +
            "wo.tolerance_width, " +
            "wo.target_thickness, " +
            "wo.tolerance_thickness, " +
            "SUM( oori.gross_weight ) AS on_line_num  " +
            "FROM " +
            "tb_work_order wo " +
            "LEFT JOIN tb_outbound_order_raw_item oori ON wo.work_order_number = oori.work_order_number  " +
            "WHERE " +
            "ISNULL( oori.parent_id )  " +
            "GROUP BY " +
            "wo.work_order_number  " +
            ") w " +
            "LEFT JOIN tb_inbound_order_product_item iopr ON w.work_order_number = iopr.work_order_number  " +
            "GROUP BY " +
            "w.work_order_number" +
            ") ww "+
            "${ew.customSqlSegment} " +
            "</script>")
    @Results(id = "woMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "work_order_number", property = "workOrderNumber"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson"),
            @Result(column = "material_id", property = "materialId"),
            @Result(column = "material_name", property = "materialName"),
            @Result(column = "num", property = "num"),
            @Result(column = "sch_start_time", property = "schStartTime"),
            @Result(column = "sch_close_time", property = "schCloseTime"),
            @Result(column = "status", property = "status"),
            @Result(column = "on_line_num", property = "onLineNum"),
            @Result(column = "output_num", property = "outputNum"),
            @Result(column = "line_id", property = "lineId"),
            @Result(column = "workflow_id", property = "workflowId"),
            @Result(column = "json_text_workflow", property = "jsonTextWorkflow"),
            @Result(column = "requirements", property = "requirements"),
            @Result(column = "target_width", property = "targetWidth"),
            @Result(column = "tolerance_width", property = "toleranceWidth"),
            @Result(column = "target_thickness", property = "targetThickness"),
            @Result(column = "tolerance_thickness", property = "toleranceThickness"),
    })
    IPage<WorkOrder> getPage(IPage<WorkOrder> page, @Param("ew") Wrapper<WorkOrder> queryWrapper);


    @Select("<script>" +
            "SELECT * FROM ("+
            "SELECT " +
            "w.*, " +
            "SUM( iopr.gross_weight ) AS output_num " +
            "FROM " +
            "( " +
            "SELECT " +
            "wo.id, " +
            "wo.work_order_number, " +
            "wo.create_time, " +
            "wo.update_time, " +
            "wo.create_person, " +
            "wo.update_person, " +
            "wo.material_id, " +
            "wo.material_name, " +
            "wo.num, " +
            "wo.sch_start_time, " +
            "wo.sch_close_time, " +
            "wo.`status`, " +
            "wo.line_id, " +
            "wo.workflow_id, " +
            "wo.json_text_workflow, " +
            "wo.requirements, " +
            "wo.target_width, " +
            "wo.tolerance_width, " +
            "wo.target_thickness, " +
            "wo.tolerance_thickness, " +
            "SUM( oori.gross_weight ) AS on_line_num  " +
            "FROM " +
            "tb_work_order wo " +
            "LEFT JOIN tb_outbound_order_raw_item oori ON wo.work_order_number = oori.work_order_number  " +
            "WHERE " +
            "ISNULL( oori.parent_id )  " +
            "GROUP BY " +
            "wo.work_order_number  " +
            ") w " +
            "LEFT JOIN tb_inbound_order_product_item iopr ON w.work_order_number = iopr.work_order_number  " +
            "GROUP BY " +
            "w.work_order_number" +
            ") ww "+
            "${ew.customSqlSegment} " +
            "</script>")
    @ResultMap("woMap")
    List<WorkOrder> getList(@Param("ew") Wrapper<WorkOrder> queryWrapper);


    /*    @Select("<script>" +
            "SELECT SUM(oori.gross_weight) " +
            "FROM " +
            "tb_work_order wo " +
            "JOIN tb_outbound_order_raw_item oori ON wo.work_order_number = oori.work_order_number " +
            "where wo.work_order_number=#{workOrderNumber} AND ISNULL(oori.parent_id) " +
            "</script>")
    Double getWorkOrderOutboundRawItemTotalGrossWeight( @Param("workOrderNumber") String workOrderNumber);


    @Select("<script>" +
            "SELECT SUM(oori.gross_weight) " +
            "FROM " +
            "tb_work_order wo " +
            "JOIN tb_outbound_order_raw_item oori ON wo.work_order_number = oori.work_order_number " +
            "where wo.work_order_number=#{workOrderNumber} AND ISNULL(oori.parent_id) " +
            "</script>")
    Double getWorkOrderInboundProductItemTotalGrossWeight( @Param("workOrderNumber") String workOrderNumber);*/


}
