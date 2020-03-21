package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.InboundOrderRawDetail;
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
}
