package com.haili.framework.domain.system.response;

import com.haili.framework.domain.system.DictType;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class DictTypeResult extends ResponseResult {
    DictType dictType;
    public DictTypeResult(ResultCode resultCode, DictType dictType) {
        super(resultCode);
        this.dictType = dictType;
    }
}
