package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haili.framework.domain.basic.OutboundOrderRawDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-19
 */
public interface OutboundOrderRawDetailMapper extends BaseMapper<OutboundOrderRawDetail> {
    @Select("<script>" +
            "SELECT tb_outbound_order_raw_detail.*, tb_material.code material_code,tb_material.name material_name " +
            "FROM tb_outbound_order_raw_detail " +
            "LEFT JOIN tb_material " +
            "ON tb_outbound_order_raw_detail.material_id=tb_material.id " +
            "${ew.customSqlSegment}" +
            "</script>")
    @Results(id = "outboundOrderRawDetailMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "material_id", property = "materialId"),
            @Result(column = "material_name", property = "materialName"),
            @Result(column = "material_code", property = "materialCode"),
            @Result(column = "outbound_order_raw_id", property = "outboundOrderRawId"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    IPage<OutboundOrderRawDetail> selectPagePreload(IPage<OutboundOrderRawDetail> page, @Param("ew") Wrapper<OutboundOrderRawDetail> queryWrapper);

    @Select("<script>" +
            "SELECT tb_outbound_order_raw_detail.*, tb_material.code material_code,tb_material.name material_name " +
            "FROM tb_outbound_order_raw_detail " +
            "LEFT JOIN tb_material " +
            "ON tb_outbound_order_raw_detail.material_id=tb_material.id " +
            "${ew.customSqlSegment}" +
            "</script>")
    @ResultMap("outboundOrderRawDetailMap")
    List<OutboundOrderRawDetail> selectListPreload(@Param("ew") Wrapper<OutboundOrderRawDetail> queryWrapper);
}
