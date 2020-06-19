package com.haili.basic.controller;

import com.haili.framework.domain.basic.DefectGroup;
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
 * @since 2019-11-15
*/
@RestController
@RequestMapping("/basic/defectgroup")
public class DefectGroupController extends CrudController<DefectGroup> {
    @Override
//    @PreAuthorize("hasAuthority('defect_group_list')")
    public QueryResponseResult<DefectGroup> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('defect_group_save')")
    public ModelResponseResult<DefectGroup> save(@RequestBody DefectGroup entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('defect_group_update')")
    public ResponseResult updateById(@RequestBody DefectGroup entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('defect_group_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
