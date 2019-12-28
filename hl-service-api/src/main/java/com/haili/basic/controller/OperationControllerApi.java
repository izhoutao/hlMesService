package com.haili.basic.controller;


import com.haili.framework.domain.basic.Operation;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.Map;

public interface OperationControllerApi {
    //数据字典
    @ApiOperation(value = "数据字典详情查询接口")
    public QueryResponseResult<Operation> list(Map<String, Object> map);

    ModelResponseResult<Operation> save(Operation entity);

    ResponseResult updateById(Operation entity);

    ResponseResult deleteById(Serializable id);
}
