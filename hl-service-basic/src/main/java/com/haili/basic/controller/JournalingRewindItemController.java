package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.basic.JournalingRewindItem;
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
 * @since 2019-12-20
*/
@RestController
@RequestMapping("/basic/journalingrewinditem")
public class JournalingRewindItemController extends CrudController<JournalingRewindItem> {

    @Override
    protected QueryWrapper<JournalingRewindItem> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<JournalingRewindItem> queryWrapper = super.extractWrapperFromRequestMap(map);
        queryWrapper.gt(!StringUtils.isEmpty(map.get("journalingBeginTime")), "begin_time", map.get("journalingBeginTime"));
        queryWrapper.lt(!StringUtils.isEmpty(map.get("journalingEndTime")), "end_time", map.get("journalingEndTime"));
        return queryWrapper;
    }


}
