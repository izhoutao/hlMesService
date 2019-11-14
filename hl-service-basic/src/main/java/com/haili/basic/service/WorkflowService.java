package com.haili.basic.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haili.basic.dao.WorkflowMappper;
import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.domain.basic.WorkflowSearchParam;
import com.haili.framework.domain.basic.response.WorkflowResult;
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
public class WorkflowService {
    @Autowired
    WorkflowMappper workflowMappper;

    public QueryResponseResult<Workflow> getWorkflows(long page, long size, WorkflowSearchParam workflowSearchParam) {
        QueryWrapper<Workflow> workflowQueryWrapper = new QueryWrapper<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("name", workflowSearchParam.getName());
//        map.put("code", workflowSearchParam.getCode());
        workflowQueryWrapper.allEq(map, false);
        Page _page = new Page(page, size);
        IPage iPage = workflowMappper.selectPage(_page, workflowQueryWrapper);
        QueryResult<Workflow> workflowQueryResult = new QueryResult<>();
        workflowQueryResult.setList(iPage.getRecords());
        workflowQueryResult.setTotal(iPage.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, workflowQueryResult);
    }

    public WorkflowResult addWorkflow(Workflow workflow) {
        if (workflow == null
//                || StringUtils.isBlank(workflow.getCode())
                || StringUtils.isBlank(workflow.getName())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Date date = new Date();
        workflow.setCreateTime(date);
        workflow.setUpdateTime(date);
        workflowMappper.insert(workflow);
        return new WorkflowResult(CommonCode.SUCCESS, workflow);
    }

    public ResponseResult updateWorkflow(String id, Workflow workflow) {
        if (workflow == null
                || StringUtils.isBlank(id)
//                || StringUtils.isBlank(workflow.getCode())
                || StringUtils.isBlank(workflow.getName())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        workflow.setId(id);
        Date date = new Date();
        workflow.setUpdateTime(date);
        workflowMappper.updateById(workflow);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public ResponseResult deleteWorkflow(String id) {
        if (StringUtils.isBlank(id)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        workflowMappper.deleteById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
