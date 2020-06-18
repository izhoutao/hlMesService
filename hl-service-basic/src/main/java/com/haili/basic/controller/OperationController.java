package com.haili.basic.controller;

import com.haili.framework.domain.basic.Operation;
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
 * @since 2019-11-17
*/
@RestController
@RequestMapping("/basic/operation")
public class OperationController extends CrudController<Operation> implements OperationControllerApi{
    @Override
//    @PreAuthorize("hasAuthority('operation_list')")
    public QueryResponseResult<Operation> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('operation_save')")
    public ModelResponseResult<Operation> save(@RequestBody Operation entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('operation_update')")
    public ResponseResult updateById(@RequestBody Operation entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('operation_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
