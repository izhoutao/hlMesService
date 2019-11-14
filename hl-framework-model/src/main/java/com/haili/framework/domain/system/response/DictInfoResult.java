package com.haili.framework.domain.system.response;

import com.haili.framework.domain.system.DictInfo;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class DictInfoResult extends ResponseResult {
    DictInfo dictInfo;
    public DictInfoResult(ResultCode resultCode, DictInfo dictInfo) {
        super(resultCode);
        this.dictInfo = dictInfo;
    }
}
