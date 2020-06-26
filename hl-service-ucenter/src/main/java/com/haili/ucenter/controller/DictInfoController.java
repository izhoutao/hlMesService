package com.haili.ucenter.controller;

import com.haili.api.system.DictInfoControllerApi;
import com.haili.framework.domain.system.DictInfo;
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
 * @since 2019-11-17
 */
@RestController
@RequestMapping("/ucenter/dictinfo")
public class DictInfoController extends CrudController<DictInfo> implements DictInfoControllerApi {
    @Override
    @PreAuthorize("hasAuthority('dictionary_save')")
    public ModelResponseResult<DictInfo> save(@RequestBody DictInfo entity) {
        if (entity.getSequenceNumber() == null) {
            entity.setSequenceNumber(1);
        }
        return super.save(entity);
    }

    @Override
//    @PreAuthorize("hasAuthority('dictionary_list')")
    public QueryResponseResult<DictInfo> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('dictionary_update')")
    public ResponseResult updateById(@RequestBody DictInfo entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('dictionary_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
