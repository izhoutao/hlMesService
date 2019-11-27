package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.ucenter.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    int insertBatchSomeColumn(List<Permission> list);


    void savePermissions(Map<String, Object> saveParams);
}
