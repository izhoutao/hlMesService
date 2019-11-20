package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.domain.ucenter.ext.RoleExt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    // List<Role> findRoleListByUserId(String userId);
    List<RoleExt> findRoleExtList();
}
