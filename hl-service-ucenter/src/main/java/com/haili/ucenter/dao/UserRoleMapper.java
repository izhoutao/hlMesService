package com.haili.ucenter.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.ucenter.UserRole;

import java.util.Map;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    void saveUserRole(Map<String, Object> saveParams);
}
