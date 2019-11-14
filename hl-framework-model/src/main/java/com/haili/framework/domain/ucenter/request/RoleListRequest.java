package com.haili.framework.domain.ucenter.request;

import com.haili.framework.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * Created by mrt on 2018/4/13.
 */
@Data
@ToString
public class RoleListRequest extends RequestData {
    //用户Id
    private String userId;
}
