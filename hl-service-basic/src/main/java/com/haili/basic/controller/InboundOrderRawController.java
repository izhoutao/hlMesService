package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.InboundOrderRaw;
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
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/basic/inboundorderraw")
public class InboundOrderRawController extends CrudController<InboundOrderRaw> {
    @Override
    protected QueryWrapper<InboundOrderRaw> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<InboundOrderRaw> inboundOrderRawQueryWrapper = super.extractWrapperFromRequestMap(map);
        Object dateRange = map.get("dateRange");
        if (dateRange instanceof List && dateRange != null && ((List) dateRange).size() == 2) {
            inboundOrderRawQueryWrapper.gt(!StringUtils.isEmpty(map.get("dateRange")), "inbound_time", ((List) dateRange).get(0));
            inboundOrderRawQueryWrapper.lt(!StringUtils.isEmpty(map.get("dateRange")), "inbound_time", ((List) dateRange).get(1));
        }
        return inboundOrderRawQueryWrapper;
    }

    @Override
//    @PreAuthorize("hasAuthority('inbound_order_raw_list')")
    public QueryResponseResult<InboundOrderRaw> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('inbound_order_raw_save')")
    public ModelResponseResult<InboundOrderRaw> save(@RequestBody InboundOrderRaw entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('inbound_order_raw_update')")
    public ResponseResult updateById(@RequestBody InboundOrderRaw entity) {
        return super.updateById(entity);
    }

    @PreAuthorize("hasAuthority('inbound_order_raw_delete')")
    @Override
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
