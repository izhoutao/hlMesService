package com.haili.basic.controller;


import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.model.response.ModelResopnseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

public interface WorkflowControllerApi {
    //数据字典
    @ApiOperation(value = "数据字典分类查询接口")
    public QueryResponseResult<Workflow> list(Map<String, Object> map);

    ModelResopnseResult<Workflow> save(Workflow entity);

    ResponseResult updateById(Workflow entity);

    ResponseResult deleteById(String id);

}
