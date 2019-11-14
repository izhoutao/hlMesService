package com.haili.api.system;

import com.haili.framework.domain.system.DictInfo;
import com.haili.framework.domain.system.DictSearchParam;
import com.haili.framework.domain.system.DictType;
import com.haili.framework.domain.system.response.DictInfoResult;
import com.haili.framework.domain.system.response.DictTypeResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

public interface DictionaryControllerApi {
    //数据字典

    @ApiOperation(value = "数据字典分类查询接口")
    public QueryResponseResult<DictType> getDictTypes(long page, long size, DictSearchParam dictSearchParam);

    @PostMapping("/dicttype")
    DictTypeResult addDictType(@RequestBody DictType dictType);

    @PutMapping("/dicttype/{id}")
    ResponseResult updateDictType(@PathVariable("id") String id, @RequestBody DictType dictType);

    @DeleteMapping("/dicttype/{id}")
    ResponseResult deleteDictType(@PathVariable("userId") String userId);

    @ApiOperation(value = "数据字典详情查询接口")
    public QueryResponseResult<DictInfo> getDictInfos(long page, long size, DictSearchParam dictSearchParam);

    @PostMapping("/dictinfo")
    DictInfoResult addDictInfo(@RequestBody DictInfo dictInfo);

    @PutMapping("/dictinfo/{id}")
    ResponseResult updateDictInfo(@PathVariable("id") String id, @RequestBody DictInfo dictInfo);

    @DeleteMapping("/dictinfo/{id}")
    ResponseResult deleteDictInfo(@PathVariable("id") String id);
}
