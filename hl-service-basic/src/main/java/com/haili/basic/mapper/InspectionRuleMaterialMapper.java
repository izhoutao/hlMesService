package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.framework.domain.basic.InspectionRuleMaterial;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-05
 */
public interface InspectionRuleMaterialMapper extends BaseMapper<InspectionRuleMaterial> {
    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_inspection_rule_material.*, tb_material.code material_code,tb_material.name material_name, tb_material_type.name material_type_name " +
            "FROM tb_inspection_rule_material " +
            "LEFT JOIN tb_material ON tb_inspection_rule_material.material_id = tb_material.id " +
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
            @Result(column = "inspection_rule_id", property = "inspectionRuleId"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    IPage<InspectionRuleMaterial> selectPagePreload(IPage<InspectionRuleMaterial> page, @Param("ew") Wrapper<InspectionRuleMaterial> queryWrapper);

    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_inspection_rule_material.*, tb_material.code material_code,tb_material.name material_name, tb_material_type.name material_type_name " +
            "FROM tb_inspection_rule_material " +
            "LEFT JOIN tb_material ON tb_inspection_rule_material.material_id = tb_material.id " +
            "LEFT JOIN tb_material_type ON tb_material_type.id = tb_material.type_id " +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @ResultMap("materialMap")
    List<InspectionRuleMaterial> selectListPreload(@Param("ew") Wrapper<InspectionRuleMaterial> queryWrapper);
}
