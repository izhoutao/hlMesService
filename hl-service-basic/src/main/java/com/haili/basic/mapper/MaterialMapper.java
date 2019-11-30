package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.framework.domain.basic.Material;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-21
 */
public interface MaterialMapper extends BaseMapper<Material> {
//    @Override
//    @Select("SELECT * FROM "+
//            "(SELECT tb_material.*, tb_material_type.name type_name " +
//            "FROM tb_material " +
//            "LEFT JOIN tb_material_type " +
//            "ON tb_material.type_id=tb_material_type.id) m ${ew.customSqlSegment}")
//    @Results(id = "materialMap", value = {
//            @Result(column = "id", property = "id"),
//            @Result(column = "type_id", property = "typeId"),
//            @Result(column = "type_name", property = "typeName"),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime"),
//    })
//    IPage<Material> selectPage(IPage<Material> page, @Param("ew") Wrapper<Material> queryWrapper);

    @Override
    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_material.*, tb_material_type.name type_name " +
            "FROM tb_material " +
            "LEFT JOIN tb_material_type " +
            "ON tb_material.type_id=tb_material_type.id" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @Results(id = "materialMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "type_id", property = "typeId"),
            @Result(column = "type_name", property = "typeName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    IPage<Material> selectPage(IPage<Material> page, @Param("ew") Wrapper<Material> queryWrapper);

    @Override
    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_material.*, tb_material_type.name type_name " +
            "FROM tb_material " +
            "LEFT JOIN tb_material_type " +
            "ON tb_material.type_id=tb_material_type.id" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @ResultMap("materialMap")
    List<Material> selectList(@Param("ew") Wrapper<Material> queryWrapper);
}
