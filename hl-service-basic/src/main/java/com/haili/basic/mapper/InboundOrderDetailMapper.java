package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.framework.domain.basic.InboundOrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-02
 */
public interface InboundOrderDetailMapper extends BaseMapper<InboundOrderDetail> {
    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_inbound_order_detail.*, tb_material.code material_code,tb_material.name material_name " +
            "FROM tb_inbound_order_detail " +
            "LEFT JOIN tb_material " +
            "ON tb_inbound_order_detail.material_id=tb_material.id" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @Results(id = "materialMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "material_id", property = "materialId"),
            @Result(column = "material_name", property = "materialName"),
            @Result(column = "material_code", property = "materialCode"),
            @Result(column = "inbound_order_id", property = "inboundOrderId"),
            @Result(column = "check_result", property = "checkResult"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    IPage<InboundOrderDetail> selectPagePreload(IPage<InboundOrderDetail> page, @Param("ew") Wrapper<InboundOrderDetail> queryWrapper);

    @Select("<script>" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            "SELECT * FROM (" +
            "</when>" +
            "SELECT tb_inbound_order_detail.*, tb_material.code material_code,tb_material.name material_name " +
            "FROM tb_inbound_order_detail " +
            "LEFT JOIN tb_material " +
            "ON tb_inbound_order_detail.material_id=tb_material.id" +
            "<when test=\"ew.customSqlSegment != ''\">" +
            ") m ${ew.customSqlSegment}" +
            "</when>" +
            "</script>")
    @ResultMap("materialMap")
    List<InboundOrderDetail> selectListPreload(@Param("ew") Wrapper<InboundOrderDetail> queryWrapper);
}
