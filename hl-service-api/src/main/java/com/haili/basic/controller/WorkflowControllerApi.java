package com.haili.basic.controller;


import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.Map;

public interface WorkflowControllerApi {
    //数据字典
    @ApiOperation(value = "数据字典分类查询接口")
    public QueryResponseResult<Workflow> list(Map<String, Object> map);

    ModelResponseResult<Workflow> save(@RequestBody Workflow entity);

    ResponseResult updateById(Workflow entity);

    ResponseResult deleteById(Serializable id);

}
