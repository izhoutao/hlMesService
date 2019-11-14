package com.haili.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.basic.Operation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationMappper extends BaseMapper<Operation> {
}
