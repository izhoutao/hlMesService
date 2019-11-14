package com.haili.ucenter.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.ucenter.User;
import com.haili.framework.domain.ucenter.ext.UserExt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserExt findUserExtByUserName(String username);
//    List<UserExt> findUserExtList(UserSearchParam userSearchParam);

}
