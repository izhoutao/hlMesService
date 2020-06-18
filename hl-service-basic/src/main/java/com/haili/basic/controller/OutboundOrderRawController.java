package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.OutboundOrderRaw;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-19
*/
@RestController
@RequestMapping("/basic/outboundorderraw")
public class OutboundOrderRawController extends CrudController<OutboundOrderRaw> {
    @Override
    protected QueryWrapper<OutboundOrderRaw> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<OutboundOrderRaw> outboundOrderRawQueryWrapper = super.extractWrapperFromRequestMap(map);
        Object dateRange = map.get("dateRange");
        if (dateRange instanceof List && dateRange != null && ((List) dateRange).size() == 2) {
            outboundOrderRawQueryWrapper.gt(!StringUtils.isEmpty(map.get("dateRange")), "outbound_time", ((List) dateRange).get(0));
            outboundOrderRawQueryWrapper.lt(!StringUtils.isEmpty(map.get("dateRange")), "outbound_time", ((List) dateRange).get(1));
        }
        return outboundOrderRawQueryWrapper;
    }

    @Override
//    @PreAuthorize("hasAuthority('outbound_order_raw_list')")
    public QueryResponseResult<OutboundOrderRaw> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_save')")
    public ModelResponseResult<OutboundOrderRaw> save(@RequestBody OutboundOrderRaw entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_update')")
    public ResponseResult updateById(@RequestBody OutboundOrderRaw entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
