package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.ucenter.UserRole;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    int insertBatchSomeColumn(List<UserRole> list);
    void saveUserRole(Map<String, Object> saveParams);
}
