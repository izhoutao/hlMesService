package com.haili.basic.controller;

import com.haili.basic.service.impl.QcDefectServiceImpl;
import com.haili.framework.domain.basic.QcDefect;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/basic/qcdefect")
public class QcDefectController extends CrudController<QcDefect> {
    @Override
//    @PreAuthorize("hasAuthority('qc_defect_list')")
    public QueryResponseResult<QcDefect> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('qc_defect_save')")
    public ModelResponseResult<QcDefect> save(@RequestBody QcDefect entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('qc_defect_update')")
    public ResponseResult updateById(@RequestBody QcDefect entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('qc_defect_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }

    @GetMapping("/top/{num}")
    public ModelResponseResult<List<QcDefect>> getTopDefects(@PathVariable("num") Integer num) {
        List<QcDefect> topDefects = ((QcDefectServiceImpl) service).getTopDefects(num);
        return new ModelResponseResult<>(CommonCode.SUCCESS, topDefects);
    }

}
