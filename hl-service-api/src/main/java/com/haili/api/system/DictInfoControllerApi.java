package com.haili.api.system;

import com.haili.framework.domain.system.DictInfo;
import com.haili.framework.model.response.ModelResopnseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

public interface DictInfoControllerApi {
    //数据字典
    @ApiOperation(value = "数据字典详情查询接口")
    public QueryResponseResult<DictInfo> list(Map<String, Object> map);

    ModelResopnseResult<DictInfo> save(DictInfo entity);

    ResponseResult updateById(DictInfo entity);

    ResponseResult deleteById(String id);
}
