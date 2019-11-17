package com.haili.api.system;

import com.haili.framework.domain.system.DictType;
import com.haili.framework.model.response.ModelResopnseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

public interface DictTypeControllerApi {
    //数据字典
    @ApiOperation(value = "数据字典分类查询接口")
    public QueryResponseResult<DictType> listByPage(Map<String, Object> map);

    ModelResopnseResult<DictType> save(DictType entity);

    ResponseResult updateById(DictType entity);

    ResponseResult deleteById(String id);

}
