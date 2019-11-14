package com.haili.framework.domain.basic.response;

import com.haili.framework.domain.basic.Operation;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class OperationResult extends ResponseResult {
    Operation operation;
    public OperationResult(ResultCode resultCode, Operation operation) {
        super(resultCode);
        this.operation = operation;
    }
}
