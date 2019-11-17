package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.dao.OperationMapper;
import com.haili.basic.service.IOperationService;
import com.haili.framework.domain.basic.Operation;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-17
 */
@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, Operation> implements IOperationService {

}
