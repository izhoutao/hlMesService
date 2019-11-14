package com.haili.framework.domain.ucenter.ext;

import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.domain.ucenter.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
@NoArgsConstructor
public class UserExt extends User {

    private static final long serialVersionUID = 6804578053507803946L;
    //权限信息
    private List<Role> roleList;

    //企业信息
    private String companyId;
}
