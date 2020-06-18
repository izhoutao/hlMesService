package com.haili.basic.controller;

import com.haili.framework.domain.basic.OutboundOrder;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-02
*/
@RestController
@RequestMapping("/basic/outboundorder")
public class OutboundOrderController extends CrudController<OutboundOrder> {
    @Override
//    @PreAuthorize("hasAuthority('outbound_order_list')")
    public QueryResponseResult<OutboundOrder> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_save')")
    public ModelResponseResult<OutboundOrder> save(@RequestBody OutboundOrder entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_update')")
    public ResponseResult updateById(@RequestBody OutboundOrder entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
