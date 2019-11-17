package com.haili.basic.controller;


import com.haili.framework.domain.basic.Operation;
import com.haili.framework.model.response.ModelResopnseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

public interface OperationControllerApi {
    //数据字典
    @ApiOperation(value = "数据字典详情查询接口")
    public QueryResponseResult<Operation> listByPage(Map<String, Object> map);

    ModelResopnseResult<Operation> save(Operation entity);

    ResponseResult updateById(Operation entity);

    ResponseResult deleteById(String id);
}
