package com.haili.framework.domain.ucenter.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by mrt on 2018/5/21.
 */
@Data
@ToString
@NoArgsConstructor
public class UserBasicInfo {

    String id;//用户id
    String staffId;//员工id
    String username;//用户名
    String name;//姓名
    String avatar;//用户头像
    //所属部门信息
    String department;//部门
    //jwt令牌
    private String jwt_token;

}
