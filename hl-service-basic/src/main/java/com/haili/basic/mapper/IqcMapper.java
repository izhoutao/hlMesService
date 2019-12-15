package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.framework.domain.basic.Iqc;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-13
 */
public interface IqcMapper extends BaseMapper<Iqc> {
    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_iqc.*, tb_material.code material_code,tb_material.name material_name " +
            "FROM tb_iqc " +
            "LEFT JOIN tb_material " +
            "ON tb_iqc.material_id=tb_material.id" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @Results(id = "iqcMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "material_id", property = "materialId"),
            @Result(column = "material_name", property = "materialName"),
            @Result(column = "material_code", property = "materialCode"),
            @Result(column = "serial_number", property = "serialNumber"),
            @Result(column = "inspect_date", property = "inspectDate"),
            @Result(column = "is_mark", property = "isMark"),
            @Result(column = "line_id", property = "lineId"),
            @Result(column = "shift_id", property = "shiftId"),
            @Result(column = "vendor_id", property = "vendorId"),
            @Result(column = "inbound_order_id", property = "inboundOrderId"),
            @Result(column = "material_type_id", property = "materialTypeId"),
            @Result(column = "customer_id", property = "customerId"),
            @Result(column = "next_operation_id", property = "nextOperationId"),
            @Result(column = "surface_grade", property = "surfaceGrade"),
            @Result(column = "check_list", property = "checkList"),
            @Result(column = "defect_list", property = "defectList"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    IPage<Iqc> selectPagePreload(IPage<Iqc> page, @Param("ew") Wrapper<Iqc> queryWrapper);

    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_iqc.*, tb_material.code material_code,tb_material.name material_name " +
            "FROM tb_iqc " +
            "LEFT JOIN tb_material " +
            "ON tb_iqc.material_id=tb_material.id" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @ResultMap("iqcMap")
    List<Iqc> selectListPreload(@Param("ew") Wrapper<Iqc> queryWrapper);
}
