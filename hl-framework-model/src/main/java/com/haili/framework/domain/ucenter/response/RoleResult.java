package com.haili.framework.domain.ucenter.response;

import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class RoleResult extends ResponseResult {
    Role role;
    public RoleResult(ResultCode resultCode,Role role) {
        super(resultCode);
        this.role = role;
    }
}
