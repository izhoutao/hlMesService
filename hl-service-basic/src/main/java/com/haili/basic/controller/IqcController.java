package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.Iqc;
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
 * @since 2019-12-13
*/
@RestController
@RequestMapping("/basic/iqc")
public class IqcController extends CrudController<Iqc> {
    @Override
    protected QueryWrapper<Iqc> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<Iqc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(map.get("isMark")), "is_mark", map.get("isMark"));
        return queryWrapper;
    }
}
