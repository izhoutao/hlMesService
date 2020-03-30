package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.JournalingFinishingTensionLevelerItem;
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
 * @since 2020-03-30
*/
@RestController
@RequestMapping("/basic/journalingfinishingtensionleveleritem")
public class JournalingFinishingTensionLevelerItemController extends CrudController<JournalingFinishingTensionLevelerItem> {
    @Override
    protected QueryWrapper<JournalingFinishingTensionLevelerItem> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<JournalingFinishingTensionLevelerItem> queryWrapper = super.extractWrapperFromRequestMap(map);
        queryWrapper.gt(!StringUtils.isEmpty(map.get("journalingBeginTime")), "begin_time", map.get("journalingBeginTime"));
        queryWrapper.lt(!StringUtils.isEmpty(map.get("journalingEndTime")), "end_time", map.get("journalingEndTime"));
        return queryWrapper;
    }
}
