package com.haili.framework.domain.ucenter.response;

import com.haili.framework.domain.ucenter.User;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserResult extends ResponseResult {
    User user;
    public UserResult(ResultCode resultCode, User user) {
        super(resultCode);
        this.user = user;
    }
}
