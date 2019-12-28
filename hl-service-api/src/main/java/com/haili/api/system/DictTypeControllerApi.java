package com.haili.api.system;

import com.haili.framework.domain.system.DictType;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.Map;

public interface DictTypeControllerApi {
    //数据字典
    @ApiOperation(value = "数据字典分类查询接口")
    public QueryResponseResult<DictType> list(Map<String, Object> map);

    ModelResponseResult<DictType> save(DictType entity);

    ResponseResult updateById(DictType entity);

    ResponseResult deleteById(Serializable id);

}
