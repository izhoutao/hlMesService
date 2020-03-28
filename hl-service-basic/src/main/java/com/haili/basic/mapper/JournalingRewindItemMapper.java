package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.basic.JournalingRewindItem;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
 */
public interface JournalingRewindItemMapper extends BaseMapper<JournalingRewindItem> {
/*    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_journaling_rewind_item.*, tb_material.code material_code,tb_material.name material_name, tb_material_type.name material_type_name " +
            "FROM tb_journaling_rewind_item " +
            "LEFT JOIN tb_material ON tb_journaling_rewind_item.material_id = tb_material.id " +
            "LEFT JOIN tb_material_type ON tb_material_type.id = tb_material.type_id " +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @Results(id = "materialMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "material_id", property = "materialId"),
            @Result(column = "material_name", property = "materialName"),
            @Result(column = "material_code", property = "materialCode"),
            @Result(column = "serial_number", property = "serialNumber"),
            @Result(column = "material_id", property = "materialId"),
            @Result(column = "vendor_id", property = "vendorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson"),
            @Result(column = "input_thickness", property = "inputThickness"),
            @Result(column = "input_weight", property = "inputWeight"),
            @Result(column = "process_velocity", property = "processVelocity"),
            @Result(column = "welder_current", property = "welderCurrent"),
            @Result(column = "welder_velocity", property = "welderVelocity"),
            @Result(column = "begin_time", property = "beginTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "output_length", property = "outputLength"),
            @Result(column = "output_weight", property = "outputWeight"),
            @Result(column = "loss_reason", property = "lossReason"),
            @Result(column = "shift_id", property = "shiftId")
    })
    IPage<JournalingRewindItem> selectPagePreload(IPage<JournalingRewindItem> page, @Param("ew") Wrapper<JournalingRewindItem> queryWrapper);

    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_journaling_rewind_item.*, tb_material.code material_code,tb_material.name material_name, tb_material_type.name material_type_name " +
            "FROM tb_journaling_rewind_item " +
            "LEFT JOIN tb_material ON tb_journaling_rewind_item.material_id = tb_material.id " +
            "LEFT JOIN tb_material_type ON tb_material_type.id = tb_material.type_id " +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @ResultMap("materialMap")
    List<JournalingRewindItem> selectListPreload(@Param("ew") Wrapper<JournalingRewindItem> queryWrapper);*/
}
