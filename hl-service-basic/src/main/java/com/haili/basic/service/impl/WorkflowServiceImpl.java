package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.dao.WorkflowMapper;
import com.haili.basic.service.IWorkflowService;
import com.haili.framework.domain.basic.Workflow;
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
public class WorkflowServiceImpl extends ServiceImpl<WorkflowMapper, Workflow> implements IWorkflowService {

}
