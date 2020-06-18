package com.haili.basic.controller;

import com.haili.framework.domain.basic.Workflow;
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
@RequestMapping("/basic/workflow")
public class WorkflowController extends CrudController<Workflow> implements WorkflowControllerApi{
    @Override
//    @PreAuthorize("hasAuthority('workflow_list')")
    public QueryResponseResult<Workflow> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('workflow_save')")
    public ModelResponseResult<Workflow> save(@RequestBody Workflow entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('workflow_update')")
    public ResponseResult updateById(@RequestBody Workflow entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('workflow_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
