package com.haili.basic.controller;

import com.haili.framework.domain.basic.JournalingGrindItem;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-08
*/
@RestController
@RequestMapping("/basic/journalinggrinditem")
public class JournalingGrindItemController extends CrudController<JournalingGrindItem> {
    @Override
//    @PreAuthorize("hasAuthority('journaling_grind_item_list')")
    public QueryResponseResult<JournalingGrindItem> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_grind_item_save')")
    public ModelResponseResult<JournalingGrindItem> save(@RequestBody JournalingGrindItem entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_grind_item_update')")
    public ResponseResult updateById(@RequestBody JournalingGrindItem entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_grind_item_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
