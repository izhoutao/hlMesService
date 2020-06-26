package com.haili.ucenter.controller;

import com.haili.api.system.DictTypeControllerApi;
import com.haili.framework.domain.system.DictInfo;
import com.haili.framework.domain.system.DictType;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.DictTypeServiceImpl;
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
 * @since 2019-11-17
 */
@RestController
@RequestMapping("/ucenter/dicttype")
public class DictTypeController extends CrudController<DictType> implements DictTypeControllerApi {
    @PostMapping("/map")
    ModelResponseResult<Map<String, List<DictInfo>>> getDictMaps(@RequestBody List<String> dictTypeIds) {
        Map<String, List<DictInfo>> map = ((DictTypeServiceImpl) this.service).getDictMaps(dictTypeIds);
        return new ModelResponseResult(CommonCode.SUCCESS, map);
    }

    @Override
//    @PreAuthorize("hasAuthority('dictionary_list')")
    public QueryResponseResult<DictType> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('dictionary_save')")
    public ModelResponseResult<DictType> save(@RequestBody DictType entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('dictionary_update')")
    public ResponseResult updateById(@RequestBody DictType entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('dictionary_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
