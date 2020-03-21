package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.InboundOrderRaw;
import com.haili.framework.web.CrudController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
