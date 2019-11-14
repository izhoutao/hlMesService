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
public class UserToken {
    String id;//用户id
    String staffId;//员工id
    String name;//用户姓名
    String avatar;//用户头像
    String department;//部门
}
