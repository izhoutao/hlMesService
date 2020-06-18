package com.haili.basic.controller;

import com.haili.framework.domain.basic.LineStation;
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
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-04
 */
@RestController
@RequestMapping("/basic/linestation")
public class LineStationController extends CrudController<LineStation> {
    @Override
//    @PreAuthorize("hasAuthority('line_station_list')")
    public QueryResponseResult<LineStation> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('line_station_save')")
    public ModelResponseResult<LineStation> save(@RequestBody LineStation entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('line_station_update')")
    public ResponseResult updateById(@RequestBody LineStation entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('line_station_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
