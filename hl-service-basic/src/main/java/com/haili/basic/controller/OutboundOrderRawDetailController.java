package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.OutboundOrderRawDetail;
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
}
