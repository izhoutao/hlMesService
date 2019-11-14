package com.haili.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.basic.Workflow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkflowMappper extends BaseMapper<Workflow> {
}
