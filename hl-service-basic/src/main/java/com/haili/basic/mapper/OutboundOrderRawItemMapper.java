package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
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
            "SELECT product_number " +
            "FROM " +
            "tb_inbound_order_raw_item " +
            "WHERE " +
            "product_number NOT IN ( " +
            "SELECT " +
            "product_number " +
            "FROM " +
            "tb_outbound_order_raw_item) " +
            "</script>")
/*    @Results(id = "outboundOrderDetailMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "product_number", property = "productNumber"),
    })*/
    List<String> getStoredRawItems();
}
