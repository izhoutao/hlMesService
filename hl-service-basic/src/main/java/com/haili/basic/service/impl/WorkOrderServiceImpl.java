package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.WorkOrderMapper;
import com.haili.basic.mapper.WorkOrderMaterialMapper;
import com.haili.basic.mapper.WorkflowMapper;
import com.haili.basic.service.IWorkOrderService;
import com.haili.framework.domain.basic.WorkOrder;
import com.haili.framework.domain.basic.WorkOrderMaterial;
import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.domain.basic.response.WorkOrderCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-02-27
 */
@Service
@Transactional
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements IWorkOrderService {
    @Autowired
    UserClient userClient;

    @Autowired
    WorkOrderMaterialMapper workOrderMaterialMapper;

    @Autowired
    WorkflowMapper workflowMapper;

    @Override
    public boolean save(WorkOrder entity) {
        if (StringUtils.isEmpty(entity.getWorkOrderNumber())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("bizName", "WORK_ORDER");
            map.put("codeRuleName", "WORK_ORDER");
            ModelResponseResult<String> result = userClient.nextSerialNumber(map);
            entity.setWorkOrderNumber(result.getModel().toString());
        }


        String workflowId = entity.getWorkflowId();
        if (StringUtils.isEmpty(workflowId)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Workflow workflow = workflowMapper.selectById(workflowId);
        String jsonTextWorkflow = workflow.getJsonTextWorkflow();
        entity.setJsonTextWorkflow(jsonTextWorkflow);

        entity.setStatus(0);
        return super.save(entity);
    }

    @Override
    public boolean updateById(WorkOrder entity) {
        String id = entity.getId();
        WorkOrder workOrder = this.baseMapper.selectById(id);
        if (workOrder.getStatus() != 0) {
            ExceptionCast.cast(WorkOrderCode.CANNOT_EDIT_NON_NEWLY_BUILT_WORK_ORDER);
        }
        String workflowId = entity.getWorkflowId();
        if (!StringUtils.isEmpty(workflowId) && !workflowId.equals(workOrder.getWorkflowId())) {
            Workflow workflow = workflowMapper.selectById(workflowId);
            String jsonTextWorkflow = workflow.getJsonTextWorkflow();
            entity.setJsonTextWorkflow(jsonTextWorkflow);
        }
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<WorkOrderMaterial> lambdaQueryWrapper = Wrappers.<WorkOrderMaterial>lambdaQuery();
        lambdaQueryWrapper.eq(WorkOrderMaterial::getWorkOrderId, id);
        return workOrderMaterialMapper.delete(lambdaQueryWrapper) >= 0;
    }

}
