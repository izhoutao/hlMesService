package com.haili.basic.controller;


import com.haili.basic.service.OperationService;
import com.haili.basic.service.WorkflowService;
import com.haili.framework.domain.basic.Operation;
import com.haili.framework.domain.basic.OperationSearchParam;
import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.domain.basic.WorkflowSearchParam;
import com.haili.framework.domain.basic.response.OperationResult;
import com.haili.framework.domain.basic.response.WorkflowResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basic")
public class BasicController implements BasicControllerApi {
    @Autowired
    OperationService operationService;
    @Autowired
    WorkflowService workflowService;
    @Override
    @GetMapping("/operation/list/{page}/{size}")
    public QueryResponseResult<Operation> getOperations(@PathVariable("page") long page, @PathVariable("size") long size, OperationSearchParam operationSearchParam) {
        return operationService.getOperations(page, size, operationSearchParam);

    }

    @Override
    @PostMapping("/operation")
    public OperationResult addOperation(@RequestBody Operation operation) {
        return operationService.addOperation(operation);
    }

    @Override
    @PutMapping("/operation/{id}")
    public ResponseResult updateOperation(@PathVariable("id") String id, @RequestBody Operation operation) {
        return operationService.updateOperation(id, operation);
    }

    @Override
    @DeleteMapping("/operation/{id}")
    public ResponseResult deleteOperation(@PathVariable("id") String id) {
        return operationService.deleteOperation(id);
    }

    @Override
    @GetMapping("/workflow/list/{page}/{size}")
    public QueryResponseResult<Workflow> getWorkflows(@PathVariable("page") long page, @PathVariable("size") long size, WorkflowSearchParam workflowSearchParam) {
        return workflowService.getWorkflows(page, size, workflowSearchParam);
    }

    @Override
    @PostMapping("/workflow")
    public WorkflowResult addWorkflow(@RequestBody Workflow workflow) {
        return workflowService.addWorkflow(workflow);
    }

    @Override
    @PutMapping("/workflow/{id}")
    public ResponseResult updateWorkflow(@PathVariable("id") String id, @RequestBody Workflow workflow) {
        return workflowService.updateWorkflow(id, workflow);
    }

    @Override
    @DeleteMapping("/workflow/{id}")
    public ResponseResult deleteWorkflow(@PathVariable("id") String id) {
        return workflowService.deleteWorkflow(id);
    }
}
