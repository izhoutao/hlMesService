package com.haili.ucenter.controller;


import com.haili.api.system.DictionaryControllerApi;
import com.haili.framework.domain.system.DictInfo;
import com.haili.framework.domain.system.DictSearchParam;
import com.haili.framework.domain.system.DictType;
import com.haili.framework.domain.system.response.DictInfoResult;
import com.haili.framework.domain.system.response.DictTypeResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.ucenter.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/ucenter")
public class DictionaryController implements DictionaryControllerApi {
    @Autowired
    DictionaryService dictionaryService;

    @Override
    @GetMapping("/dicttype/list/{page}/{size}")
    public QueryResponseResult<DictType> getDictTypes(@PathVariable("page") long page, @PathVariable("size") long size, DictSearchParam dictSearchParam) {
        return dictionaryService.getDictTypes(page, size, dictSearchParam);

    }

    @Override
    @PostMapping("/dicttype")
    public DictTypeResult addDictType(@RequestBody DictType dictType) {
        return dictionaryService.addDictType(dictType);
    }

    @Override
    @PutMapping("/dicttype/{id}")
    public ResponseResult updateDictType(@PathVariable("id") String id, @RequestBody DictType dictType) {
        return dictionaryService.updateDictType(id, dictType);
    }

    @Override
    @DeleteMapping("/dicttype/{id}")
    public ResponseResult deleteDictType(@PathVariable("id") String id) {
        return dictionaryService.deleteDictType(id);
    }

    @Override
    @GetMapping("/dictinfo/list/{page}/{size}")
    public QueryResponseResult<DictInfo> getDictInfos(@PathVariable("page") long page, @PathVariable("size") long size, DictSearchParam dictSearchParam) {
        return dictionaryService.getDictInfos(page, size, dictSearchParam);
    }

    @Override
    @PostMapping("/dictinfo")
    public DictInfoResult addDictInfo(@RequestBody DictInfo dictInfo) {
        return dictionaryService.addDictInfo(dictInfo);
    }

    @Override
    @PutMapping("/dictinfo/{id}")
    public ResponseResult updateDictInfo(@PathVariable("id") String id, @RequestBody DictInfo dictInfo) {
        return dictionaryService.updateDictInfo(id, dictInfo);
    }

    @Override
    @DeleteMapping("/dictinfo/{id}")
    public ResponseResult deleteDictInfo(@PathVariable("id") String id) {
        return dictionaryService.deleteDictInfo(id);
    }
}
