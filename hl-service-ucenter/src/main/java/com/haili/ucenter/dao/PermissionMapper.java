package com.haili.ucenter.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.ucenter.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    void savePermissions(Map<String, Object> saveParams);
}
