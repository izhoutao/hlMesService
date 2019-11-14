package com.haili.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haili.basic.dao.OperationMappper;
import com.haili.framework.domain.basic.Operation;
import com.haili.framework.domain.basic.OperationSearchParam;
import com.haili.framework.domain.basic.response.OperationResult;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.QueryResult;
import com.haili.framework.model.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

@Service
@Transactional
public class OperationService {
    @Autowired
    OperationMappper operationMappper;

    public QueryResponseResult<Operation> getOperations(long page, long size, OperationSearchParam operationSearchParam) {
        QueryWrapper<Operation> operationQueryWrapper = new QueryWrapper<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("name", operationSearchParam.getName());
        map.put("code", operationSearchParam.getCode());
        operationQueryWrapper.allEq(map, false);
        Page _page = new Page(page, size);
        IPage iPage = operationMappper.selectPage(_page, operationQueryWrapper);
        QueryResult<Operation> operationQueryResult = new QueryResult<>();
        operationQueryResult.setList(iPage.getRecords());
        operationQueryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, operationQueryResult);
    }

    public OperationResult addOperation(Operation operation) {
        if (operation == null
                || StringUtils.isBlank(operation.getCode())
                || StringUtils.isBlank(operation.getName())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Date date = new Date();
        operation.setCreateTime(date);
        operation.setUpdateTime(date);
        operationMappper.insert(operation);
        return new OperationResult(CommonCode.SUCCESS, operation);
    }

    public ResponseResult updateOperation(String id, Operation operation) {
        if (operation == null
                || StringUtils.isBlank(id)
                || StringUtils.isBlank(operation.getCode())
                || StringUtils.isBlank(operation.getName())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        operation.setId(id);
        Date date = new Date();
        operation.setUpdateTime(date);
        operationMappper.updateById(operation);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public ResponseResult deleteOperation(String id) {
        if (StringUtils.isBlank(id)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        operationMappper.deleteById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
