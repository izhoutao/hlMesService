package com.haili.basic.controller;

import com.haili.framework.domain.basic.Shift;
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
@RequestMapping("/basic/shift")
public class ShiftController extends CrudController<Shift> {
    @Override
//    @PreAuthorize("hasAuthority('shift_list')")
    public QueryResponseResult<Shift> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('shift_save')")
    public ModelResponseResult<Shift> save(@RequestBody Shift entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('shift_update')")
    public ResponseResult updateById(@RequestBody Shift entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('shift_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
