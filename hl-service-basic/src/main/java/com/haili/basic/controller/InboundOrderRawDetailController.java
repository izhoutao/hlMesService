package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.InboundOrderRawDetail;
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
 * @since 2020-03-12
*/
@RestController
@RequestMapping("/basic/inboundorderrawdetail")
public class InboundOrderRawDetailController extends CrudController<InboundOrderRawDetail> {
    @Override
    protected QueryWrapper<InboundOrderRawDetail> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<InboundOrderRawDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("inboundOrderRawId")), "inbound_order_raw_id", map.get("inboundOrderRawId"));
        return queryWrapper;
    }

    @Override
//    @PreAuthorize("hasAuthority('inbound_order_raw_list')")
    public QueryResponseResult<InboundOrderRawDetail> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('inbound_order_raw_save')")
    public ModelResponseResult<InboundOrderRawDetail> save(@RequestBody InboundOrderRawDetail entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('inbound_order_raw_update')")
    public ResponseResult updateById(@RequestBody InboundOrderRawDetail entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('inbound_order_raw_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
