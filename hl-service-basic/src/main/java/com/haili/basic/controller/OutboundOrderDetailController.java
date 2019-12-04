package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.OutboundOrderDetail;
import com.haili.framework.web.CrudController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-02
 */
@RestController
@RequestMapping("/basic/outboundorderdetail")
public class OutboundOrderDetailController extends CrudController<OutboundOrderDetail> {
    @Override
    protected QueryWrapper<OutboundOrderDetail> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<OutboundOrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("outboundOrderId")), "outbound_order_id", map.get("outboundOrderId"));
        return queryWrapper;
    }
}
