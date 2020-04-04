package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-19
 */
public interface OutboundOrderRawItemMapper extends BaseMapper<OutboundOrderRawItem> {
    @Select("<script> " +
            "SELECT inbound.product_number AS product_number " +
            "FROM tb_inbound_order_raw_item inbound, tb_outbound_order_raw_item outbound " +
            "WHERE inbound.product_number = outbound.product_number AND UNIX_TIMESTAMP( inbound.time ) > UNIX_TIMESTAMP( outbound.time)" +
            "</script>")
/*    @Results(id = "outboundOrderDetailMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "product_number", property = "productNumber"),
    })*/
    List<String> getStoredRawItems();

    @Select("<script> " +
            "SELECT inbound.* " +
            "FROM tb_inbound_order_raw_item inbound, tb_outbound_order_raw_item outbound " +
            "WHERE " +
            "inbound.product_number = outbound.product_number " +
            "AND UNIX_TIMESTAMP( inbound.time ) > UNIX_TIMESTAMP( outbound.time) " +
            "AND inbound.product_number = #{productNumber} " +
            "</script>")
    @Results(id = "inboundOrderItemMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "inbound_order_raw_id", property = "inboundOrderRawId"),
            @Result(column = "inbound_order_raw_detail_id", property = "inboundOrderRawDetailId"),
            @Result(column = "material_number", property = "materialNumber"),
            @Result(column = "product_number", property = "productNumber"),
            @Result(column = "steel_grade", property = "steelGrade"),
            @Result(column = "surface_finish", property = "surfaceFinish"),
            @Result(column = "width", property = "width"),
            @Result(column = "thickness", property = "thickness"),
            @Result(column = "length", property = "length"),
            @Result(column = "label_specification", property = "labelSpecification"),
            @Result(column = "specification", property = "specification"),
            @Result(column = "label_net_weight", property = "labelNetWeight"),
            @Result(column = "label_gross_weight", property = "labelGrossWeight"),
            @Result(column = "net_weight", property = "netWeight"),
            @Result(column = "gross_weight", property = "grossWeight"),
            @Result(column = "edge", property = "edge"),
            @Result(column = "grade", property = "grade"),
            @Result(column = "inspector", property = "inspector"),
            @Result(column = "barcode", property = "barcode"),
            @Result(column = "time", property = "time"),
            @Result(column = "description", property = "description"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson")
    })
    InboundOrderRawItem getStoredRawItem(String productNumber);




}
