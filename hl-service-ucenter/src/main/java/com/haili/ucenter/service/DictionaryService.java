package com.haili.ucenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haili.framework.domain.system.DictInfo;
import com.haili.framework.domain.system.DictSearchParam;
import com.haili.framework.domain.system.DictType;
import com.haili.framework.domain.system.response.DictInfoResult;
import com.haili.framework.domain.system.response.DictTypeResult;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.QueryResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.ucenter.dao.DictInfoMappper;
import com.haili.ucenter.dao.DictTypeMappper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Service
@Transactional
public class DictionaryService {
    @Autowired
    DictTypeMappper dictTypeMappper;
    @Autowired
    DictInfoMappper dictInfoMappper;

    public QueryResponseResult<DictType> getDictTypes(long page, long size, DictSearchParam dictSearchParam) {
        QueryWrapper<DictType> dictTypeQueryWrapper = new QueryWrapper<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("name", dictSearchParam.getName());
        map.put("code", dictSearchParam.getCode());
        dictTypeQueryWrapper.allEq(map, false);
        Page _page = new Page(page, size);
        IPage iPage = dictTypeMappper.selectPage(_page, dictTypeQueryWrapper);
        QueryResult<DictType> dictTypeQueryResult = new QueryResult<>();
        dictTypeQueryResult.setList(iPage.getRecords());
        dictTypeQueryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, dictTypeQueryResult);
    }

    public QueryResponseResult<DictInfo> getDictInfos(long page, long size, DictSearchParam dictSearchParam) {
        if(StringUtils.isBlank(dictSearchParam.getTypeId())){
            QueryResult<DictInfo> dictInfoQueryResult = new QueryResult<>();
            dictInfoQueryResult.setList(new ArrayList<DictInfo>());
            dictInfoQueryResult.setTotal(0);
            return new QueryResponseResult(CommonCode.SUCCESS, dictInfoQueryResult);
        }
        QueryWrapper<DictInfo> dictInfoQueryWrapper = new QueryWrapper<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("name", dictSearchParam.getName());
        map.put("code", dictSearchParam.getCode());
        map.put("type_id", dictSearchParam.getTypeId());
        dictInfoQueryWrapper.allEq(map, false);
        Page _page = new Page(page, size);
        IPage iPage = dictInfoMappper.selectPage(_page, dictInfoQueryWrapper);
        QueryResult<DictInfo> dictInfoQueryResult = new QueryResult<>();
        dictInfoQueryResult.setList(iPage.getRecords());
        dictInfoQueryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, dictInfoQueryResult);
    }

    public DictTypeResult addDictType(DictType dictType) {
        if (dictType == null
                || StringUtils.isBlank(dictType.getCode())
                || StringUtils.isBlank(dictType.getName())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
/*        QueryWrapper<DictType> dictTypeQueryWrapper = new QueryWrapper<>();
        dictTypeQueryWrapper.eq("code", dictType.getCode());
        if (dictTypeMappper.selectOne(dictTypeQueryWrapper) != null) {
            ExceptionCast.cast(DictCode.DICT_TYPE_ALREADY_EXISTS);
        }*/
        Date date = new Date();
        dictType.setCreateTime(date);
        dictType.setUpdateTime(date);
        dictTypeMappper.insert(dictType);
        return new DictTypeResult(CommonCode.SUCCESS, dictType);
    }

    public ResponseResult updateDictType(String id, DictType dictType) {
        if (dictType == null
                || StringUtils.isBlank(id)
                || StringUtils.isBlank(dictType.getCode())
                || StringUtils.isBlank(dictType.getName())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        dictType.setId(id);
        Date date = new Date();
        dictType.setUpdateTime(date);
        dictTypeMappper.updateById(dictType);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public ResponseResult deleteDictType(String id) {
        if (StringUtils.isBlank(id)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        dictTypeMappper.deleteById(id);
        QueryWrapper<DictInfo> dictInfoQueryWrapper = new QueryWrapper<>();
        dictInfoQueryWrapper.eq("type_id", id);
        dictInfoMappper.delete(dictInfoQueryWrapper);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public DictInfoResult addDictInfo(DictInfo dictInfo) {
        if (dictInfo == null
                || StringUtils.isBlank(dictInfo.getTypeId())
                || StringUtils.isBlank(dictInfo.getCode())
                || StringUtils.isBlank(dictInfo.getName())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        dictInfoMappper.insert(dictInfo);
        return new DictInfoResult(CommonCode.SUCCESS, dictInfo);
    }

    public ResponseResult updateDictInfo(String id, DictInfo dictInfo) {
        if (dictInfo == null
                || StringUtils.isBlank(id)
                || StringUtils.isBlank(dictInfo.getCode())
                || StringUtils.isBlank(dictInfo.getName())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        dictInfo.setId(id);
        dictInfoMappper.updateById(dictInfo);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public ResponseResult deleteDictInfo(String id) {
        if (StringUtils.isBlank(id)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        dictInfoMappper.deleteById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
