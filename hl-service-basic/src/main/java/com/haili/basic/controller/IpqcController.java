package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.Ipqc;
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
 * @since 2019-12-15
*/
@RestController
@RequestMapping("/basic/ipqc")
public class IpqcController extends CrudController<Ipqc> {
    @Override
    protected QueryWrapper<Ipqc> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<Ipqc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("isMark")), "is_mark", map.get("isMark"));
        return queryWrapper;
    }
}
