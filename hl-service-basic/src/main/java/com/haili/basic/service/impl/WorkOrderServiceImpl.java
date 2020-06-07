package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.*;
import com.haili.basic.service.IWorkOrderService;
import com.haili.framework.domain.basic.*;
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
import java.util.List;

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

    @Autowired
    InboundOrderProductItemMapper inboundOrderProductItemMapper;

    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;

    @Override
    public List<WorkOrder> list(Wrapper<WorkOrder> queryWrapper) {
        return this.baseMapper.getList(queryWrapper);
    }

    @Override
    public IPage<WorkOrder> page(IPage<WorkOrder> page, Wrapper<WorkOrder> queryWrapper) {
        return this.baseMapper.getPage(page, queryWrapper);
    }

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
        if (entity.getStatus() != null && (entity.getStatus() == 2 || entity.getStatus() == 3)) {
            ExceptionCast.cast(WorkOrderCode.CANNOT_EDIT_NON_NEWLY_BUILT_WORK_ORDER);
        }
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
        entity.setOnLineNum(null);
        entity.setOutputNum(null);
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<WorkOrderMaterial> lambdaQueryWrapper = Wrappers.<WorkOrderMaterial>lambdaQuery();
        lambdaQueryWrapper.eq(WorkOrderMaterial::getWorkOrderId, id);
        return workOrderMaterialMapper.delete(lambdaQueryWrapper) >= 0;
    }


    public int updateWorkOrder(String workOrderNumber) {
/*        LambdaQueryWrapper<WorkOrder> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(WorkOrder::getWorkOrderNumber,workOrderNumber);*/

        LambdaQueryWrapper<InboundOrderProductItem> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderProductItem::getWorkOrderNumber, workOrderNumber);
        List<InboundOrderProductItem> inboundOrderProductItems = inboundOrderProductItemMapper.selectList(lambdaQueryWrapper);
        Float outputNum = inboundOrderProductItems.stream()
                .map(item -> item.getGrossWeight())
                .reduce(Float::sum)
                .orElse(0f);

        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper1 = Wrappers.lambdaQuery();
        lambdaQueryWrapper1.eq(OutboundOrderRawItem::getWorkOrderNumber, workOrderNumber);
        List<OutboundOrderRawItem> outboundOrderRawItems = outboundOrderRawItemMapper.selectList(lambdaQueryWrapper1);
        Integer status = 3;
        int size = outboundOrderRawItems.size();
        if (size == 0) {
            status = null;
        } else {
            for (int i = 0; i < size; i++) {
                OutboundOrderRawItem item = outboundOrderRawItems.get(i);
                if (item.getStatus() == 0 && !"".equals(item.getNextOperationLabel())) {
                    status = 2;
                    break;
                }
            }
        }
        Float onLineNum = outboundOrderRawItems.stream()
                .filter(item -> item.getParentId() == null)
                .map(item -> item.getGrossWeight())
                .reduce(Float::sum)
                .orElse(0f);
        LambdaUpdateWrapper<WorkOrder> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(WorkOrder::getWorkOrderNumber, workOrderNumber)
                .set(WorkOrder::getOnLineNum, onLineNum)
                .set(WorkOrder::getOutputNum, outputNum)
                .set(status != null, WorkOrder::getStatus, status);

        return this.baseMapper.update(null, lambdaUpdateWrapper);
    }

}
