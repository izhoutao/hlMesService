package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import org.apache.ibatis.annotations.Param;
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
            "SELECT inbound.* " +
            "FROM tb_inbound_order_raw_item inbound LEFT JOIN tb_outbound_order_raw_item outbound " +
            "ON inbound.product_number = outbound.product_number " +
            "${ew.customSqlSegment} " +
            "<choose> " +
            "<when test=\"ew != null\"> " +
            "AND " +
            "</when> " +
            "<otherwise> " +
            "WHERE " +
            "</otherwise> " +
            "</choose> " +
            "(ISNULL(outbound.time) OR UNIX_TIMESTAMP(inbound.receiving_time) > UNIX_TIMESTAMP(outbound.time)) " +
            "</script>")
/*    @Results(id = "outboundOrderDetailMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "product_number", property = "productNumber"),
    })*/
    List<InboundOrderRawItem> getStoredRawItems(@Param(Constants.WRAPPER) Wrapper<InboundOrderRawItem> queryWrapper);

    @Select("<script> " +
            "SELECT inbound.* " +
            "FROM tb_inbound_order_raw_item inbound LEFT JOIN tb_outbound_order_raw_item outbound " +
            "ON inbound.product_number = outbound.product_number " +
            "WHERE (ISNULL(outbound.time) OR UNIX_TIMESTAMP(inbound.receiving_time) > UNIX_TIMESTAMP(outbound.time)) " +
            "AND inbound.product_number = #{productNumber} " +
            "</script>")
    @Results(id = "inboundOrderItemMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "receiving_time", property = "receivingTime"),
            @Result(column = "material_number", property = "materialNumber"),
            @Result(column = "product_number", property = "productNumber"),
            @Result(column = "steel_grade", property = "steelGrade"),
            @Result(column = "source", property = "source"),
            @Result(column = "thickness", property = "thickness"),
            @Result(column = "width", property = "width"),
            @Result(column = "gross_weight", property = "grossWeight"),
            @Result(column = "net_weight", property = "netWeight"),
            @Result(column = "gross_weight2", property = "grossWeight2"),
            @Result(column = "package_weight", property = "packageWeight"),
            @Result(column = "contract_number", property = "contractNumber"),
            @Result(column = "customer_id", property = "customerId"),
            @Result(column = "customer_name", property = "customerName"),
            @Result(column = "order_time", property = "orderTime"),
            @Result(column = "order_thickness", property = "orderThickness"),
            @Result(column = "rolling_thickness", property = "rollingThickness"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_person", property = "createPerson"),
            @Result(column = "update_person", property = "updatePerson"),
    })
    InboundOrderRawItem getStoredRawItem(String productNumber);

    @Select("<script> " +
            "SELECT " +
            "o.material_number, " +
            "o.product_number, " +
            "o.work_order_number, " +
            "o.json_text_workflow, " +
            "o.next_operation_label, " +
            "w.sch_close_time " +
            "FROM tb_outbound_order_raw_item o JOIN tb_work_order w " +
            "ON o.work_order_number = w.work_order_number " +
            "${ew.customSqlSegment}" +
            "</script>")
    IPage<OutboundOrderRawItem> getOperationBoardPage(IPage<OutboundOrderRawItem> page, @Param(Constants.WRAPPER) Wrapper<OutboundOrderRawItem> queryWrapper);

}
