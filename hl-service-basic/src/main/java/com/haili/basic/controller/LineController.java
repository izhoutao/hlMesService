package com.haili.basic.controller;

import com.haili.framework.domain.basic.Line;
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
 * @since 2019-11-18
*/
@RestController
@RequestMapping("/basic/line")
public class LineController extends CrudController<Line> {
    @Override
//    @PreAuthorize("hasAuthority('line_list')")
    public QueryResponseResult<Line> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('line_save')")
    public ModelResponseResult<Line> save(@RequestBody Line entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('line_uodate')")
    public ResponseResult updateById(@RequestBody Line entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('line_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
