package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.OutboundOrderRaw;
import com.haili.framework.web.CrudController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
