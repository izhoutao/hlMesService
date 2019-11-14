package com.haili.basic.controller;

import com.haili.framework.domain.basic.Operation;
import com.haili.framework.domain.basic.OperationSearchParam;
import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.domain.basic.WorkflowSearchParam;
import com.haili.framework.domain.basic.response.OperationResult;
import com.haili.framework.domain.basic.response.WorkflowResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;

public interface BasicControllerApi {
    QueryResponseResult<Operation> getOperations(long page, long size, OperationSearchParam operationSearchParam);

    OperationResult addOperation(Operation operation);

    ResponseResult updateOperation(String id, Operation operation);

    ResponseResult deleteOperation(String id);

    QueryResponseResult<Workflow> getWorkflows(long page, long size, WorkflowSearchParam workflowSearchParam);

    WorkflowResult addWorkflow(Workflow workflow);

    ResponseResult updateWorkflow(String id, Workflow workflow);

    ResponseResult deleteWorkflow(String id);
}
