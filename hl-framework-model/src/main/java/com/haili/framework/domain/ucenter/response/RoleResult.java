package com.haili.framework.domain.ucenter.response;

import com.haili.framework.domain.ucenter.ext.RoleExt;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class RoleResult extends ResponseResult {
    RoleExt roleExt;
    public RoleResult(ResultCode resultCode,RoleExt roleExt) {
        super(resultCode);
        this.roleExt = roleExt;
    }
}
