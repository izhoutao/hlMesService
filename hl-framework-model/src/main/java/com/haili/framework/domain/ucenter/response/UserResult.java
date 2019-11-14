package com.haili.framework.domain.ucenter.response;

import com.haili.framework.domain.ucenter.ext.UserExt;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserResult extends ResponseResult {
    UserExt userExt;
    public UserResult(ResultCode resultCode, UserExt userExt) {
        super(resultCode);
        this.userExt = userExt;
    }
}
