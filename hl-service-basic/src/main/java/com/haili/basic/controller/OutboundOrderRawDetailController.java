package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.OutboundOrderRawDetail;
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
@RequestMapping("/basic/outboundorderrawdetail")
public class OutboundOrderRawDetailController extends CrudController<OutboundOrderRawDetail> {
    @Override
    protected QueryWrapper<OutboundOrderRawDetail> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<OutboundOrderRawDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("outboundOrderRawId")), "outbound_order_raw_id", map.get("outboundOrderRawId"));
        queryWrapper.eq(!StringUtils.isEmpty(map.get("workOrderNumber")), "work_order_number", map.get("workOrderNumber"));
        return queryWrapper;
    }

    @Override
//    @PreAuthorize("hasAuthority('outbound_order_raw_detail_list')")
    public QueryResponseResult<OutboundOrderRawDetail> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_save')")
    public ModelResponseResult<OutboundOrderRawDetail> save(@RequestBody OutboundOrderRawDetail entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_update')")
    public ResponseResult updateById(@RequestBody OutboundOrderRawDetail entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
