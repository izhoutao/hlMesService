package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.Ipqc;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/basic/ipqc")
public class IpqcController extends CrudController<Ipqc> {

    @Override
    @PreAuthorize("hasAuthority('ipqc')")
    public QueryResponseResult<Ipqc> list(Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    protected QueryWrapper<Ipqc> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<Ipqc> queryWrapper = super.extractWrapperFromRequestMap(map);
        queryWrapper.eq(!StringUtils.isEmpty(map.get("productNumber")), "item.product_number", map.get("productNumber"));
        queryWrapper.eq(!StringUtils.isEmpty(map.get("operation")), "item.operation", map.get("operation"));
        queryWrapper.isNull(!StringUtils.isEmpty(map.get("inspectorConfirm"))&&((Integer)map.get("inspectorConfirm")==0), "inspector");
        queryWrapper.isNotNull(!StringUtils.isEmpty(map.get("inspectorConfirm"))&&((Integer)map.get("inspectorConfirm")==1), "inspector");
        queryWrapper.eq(!StringUtils.isEmpty(map.get("inspectorResult")), "inspector_result", map.get("inspectorResult"));
        Object dateRange = map.get("dateRange");
        if (dateRange instanceof List && dateRange != null && ((List) dateRange).size() == 2) {
            queryWrapper.gt(!StringUtils.isEmpty(map.get("dateRange")), "item.date", ((List) dateRange).get(0));
            queryWrapper.lt(!StringUtils.isEmpty(map.get("dateRange")), "item.date", ((List) dateRange).get(1));
        }
        return queryWrapper;
    }
}
