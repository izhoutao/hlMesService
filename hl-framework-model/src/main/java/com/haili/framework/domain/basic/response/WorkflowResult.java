package com.haili.framework.domain.basic.response;

import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class WorkflowResult extends ResponseResult {
    Workflow workflow;
    public WorkflowResult(ResultCode resultCode, Workflow workflow) {
        super(resultCode);
        this.workflow = workflow;
    }
}
