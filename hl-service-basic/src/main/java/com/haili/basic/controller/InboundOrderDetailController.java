package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.InboundOrderDetail;
import com.haili.framework.web.CrudController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/basic/inboundorderdetail")
public class InboundOrderDetailController extends CrudController<InboundOrderDetail> {
    @Override
    protected QueryWrapper<InboundOrderDetail> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<InboundOrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("inboundOrderId")), "inbound_order_id", map.get("inboundOrderId"));
        return queryWrapper;
    }
}
