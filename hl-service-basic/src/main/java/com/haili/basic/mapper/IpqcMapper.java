package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.framework.domain.basic.Ipqc;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-04-02
 */
public interface IpqcMapper extends BaseMapper<Ipqc> {

    @Select("<script>" +
            "SELECT " +
            "item.*, " +
            "ipqc.id, " +
//            "ipqc.inspect_date, " +
            "ipqc.hot_roll_origin, " +
            "ipqc.next_operation, " +
//            "ipqc.shift_id, " +
            "ipqc.steel_grade, " +
            "ipqc.surface_finish, " +
            "ipqc.uses, " +
            "ipqc.customer_id, " +
            "ipqc.grade, " +
            "ipqc.harmful_defect_percent, " +
            "ipqc.grade_score, " +
            "ipqc.anneal_tv, " +
            "ipqc.anneal_hardness, " +
            "ipqc.rolling_pass, " +
            "ipqc.recommended_surface, " +
            "ipqc.unwind_method, " +
            "ipqc.handover_matters, " +
            "ipqc.note, " +
            "ipqc.inspector, " +
            "ipqc.inspector_name, " +
            "ipqc.inspector_result, " +
            "ipqc.checker, " +
            "ipqc.checker_name, " +
            "ipqc.checker_result, " +
            "ipqc.measurement, " +
            "ipqc.defect_list, " +
            "ipqc.status " +
            "FROM " +
            "(SELECT '重卷' AS operation, product_number,material_number, date, shift_id, id AS item_id FROM tb_journaling_rewind_item UNION " +
//            "SELECT '轧机' AS operation, product_number,material_number, date, shift_id, id AS item_id FROM tb_journaling_rolling_mill_item UNION " +
            "SELECT '退火炉' AS operation, product_number,material_number, date, shift_id, id AS item_id FROM tb_journaling_anneal_item UNION " +
            "SELECT '精整拉矫' AS operation, product_number,material_number, date, shift_id, id AS item_id FROM tb_journaling_finishing_tension_leveler_item ) " +
            "item LEFT JOIN tb_ipqc ipqc ON item.product_number = ipqc.product_number AND item.operation = ipqc.operation AND item.date = ipqc.date AND item.shift_id = ipqc.shift_id " +
            "${ew.customSqlSegment}" +
            "</script>")
    @Results(id = "ipqcMap", value = {
            @Result(column = "id", property = "id"),
//            @Result(column = "inspect_date", property = "inspectDate"),
            @Result(column = "date", property = "date"),
            @Result(column = "shift_id", property = "shiftId"),
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "hot_roll_origin", property = "hotRollOrigin"),
            @Result(column = "operation", property = "operation"),
            @Result(column = "next_operation", property = "nextOperation"),
//            @Result(column = "shift_id", property = "shiftId"),
            @Result(column = "steel_grade", property = "steelGrade"),
            @Result(column = "surface_finish", property = "surfaceFinish"),
            @Result(column = "uses", property = "uses"),
            @Result(column = "customer_id", property = "customerId"),
            @Result(column = "product_number", property = "productNumber"),
            @Result(column = "material_number", property = "materialNumber"),
            @Result(column = "grade", property = "grade"),
            @Result(column = "harmful_defect_percent", property = "harmfulDefectPercent"),
            @Result(column = "grade_score", property = "gradeScore"),
            @Result(column = "anneal_tv", property = "annealTv"),
            @Result(column = "anneal_hardness", property = "annealHardness"),
            @Result(column = "rolling_pass", property = "rollingPass"),
            @Result(column = "recommended_surface", property = "recommendedSurface"),
            @Result(column = "unwind_method", property = "unwindMethod"),
            @Result(column = "handover_matters", property = "handoverMatters"),
            @Result(column = "note", property = "note"),
            @Result(column = "inspector", property = "inspector"),
            @Result(column = "inspector_name", property = "inspectorName"),
            @Result(column = "inspector_result", property = "inspectorResult"),
            @Result(column = "checker", property = "checker"),
            @Result(column = "checker_name", property = "checkerName"),
            @Result(column = "checker_result", property = "checkerResult"),
            @Result(column = "measurement", property = "measurement"),
            @Result(column = "defect_list", property = "defectList"),
            @Result(column = "status", property = "status"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson")
    })
    IPage<Ipqc> getPage(IPage<Ipqc> page, @Param("ew") Wrapper<Ipqc> queryWrapper);


    @Select("<script>" +
            "SELECT " +
            "item.*, " +
            "ipqc.id, " +
//            "ipqc.inspect_date, " +
            "ipqc.hot_roll_origin, " +
            "ipqc.next_operation, " +
//            "ipqc.shift_id, " +
            "ipqc.steel_grade, " +
            "ipqc.surface_finish, " +
            "ipqc.uses, " +
            "ipqc.customer_id, " +
            "ipqc.grade, " +
            "ipqc.harmful_defect_percent, " +
            "ipqc.grade_score, " +
            "ipqc.anneal_tv, " +
            "ipqc.anneal_hardness, " +
            "ipqc.rolling_pass, " +
            "ipqc.recommended_surface, " +
            "ipqc.unwind_method, " +
            "ipqc.handover_matters, " +
            "ipqc.note, " +
            "ipqc.inspector, " +
            "ipqc.inspector_name, " +
            "ipqc.inspector_result, " +
            "ipqc.checker, " +
            "ipqc.checker_name, " +
            "ipqc.checker_result, " +
            "ipqc.measurement, " +
            "ipqc.defect_list, " +
            "ipqc.status " +
            "FROM " +
            "(SELECT '重卷' AS operation, product_number,material_number, date, shift_id, id AS item_id FROM tb_journaling_rewind_item UNION " +
//            "SELECT '轧机' AS operation, product_number,material_number, date, shift_id, id AS item_id FROM tb_journaling_rolling_mill_item UNION " +
            "SELECT '退火炉' AS operation, product_number,material_number, date, shift_id, id AS item_id FROM tb_journaling_anneal_item UNION " +
            "SELECT '精整拉矫' AS operation, product_number,material_number, date, shift_id, id AS item_id FROM tb_journaling_finishing_tension_leveler_item ) " +
            "item LEFT JOIN tb_ipqc ipqc ON item.product_number = ipqc.product_number AND item.operation = ipqc.operation AND item.date = ipqc.date AND item.shift_id = ipqc.shift_id " +
            "${ew.customSqlSegment}" +
            "</script>")
    @ResultMap("ipqcMap")
    List<Ipqc> getList(@Param("ew") Wrapper<Ipqc> queryWrapper);
}
