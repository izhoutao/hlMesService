package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.ProductionDefect;
import com.haili.framework.web.CrudController;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * VIEW 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-04
*/
@RestController
@RequestMapping("/basic/productiondefect")
public class ProductionDefectController extends CrudController<ProductionDefect> {
    @Override
    protected QueryWrapper<ProductionDefect> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<ProductionDefect> queryWrapper = super.extractWrapperFromRequestMap(map);
/*        queryWrapper.eq(!StringUtils.isEmpty(map.get("productNumber")), "item.product_number", map.get("productNumber"));
        queryWrapper.eq(!StringUtils.isEmpty(map.get("operation")), "item.operation", map.get("operation"));
        queryWrapper.eq(!StringUtils.isEmpty(map.get("degree")), "item.degree", map.get("degree"));
        queryWrapper.eq(!StringUtils.isEmpty(map.get("grade")), "item.grade", map.get("grade"));*/
        Object dateRange = map.get("dateRange");
        if (dateRange instanceof List && dateRange != null && ((List) dateRange).size() == 2) {
            queryWrapper.gt(!StringUtils.isEmpty(map.get("dateRange")), "date", ((List) dateRange).get(0));
            queryWrapper.lt(!StringUtils.isEmpty(map.get("dateRange")), "date", ((List) dateRange).get(1));
        }
        return queryWrapper;
    }
}
