package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.mapper.WorkOrderMapper;
import com.haili.basic.service.IOutboundOrderRawItemService;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.WorkOrder;
import com.haili.framework.domain.basic.response.OutboundOrderRawCode;
import com.haili.framework.domain.basic.response.WorkOrderCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.utils.WorkflowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-19
 */
@Service
@Transactional
public class OutboundOrderRawItemServiceImpl extends ServiceImpl<OutboundOrderRawItemMapper, OutboundOrderRawItem> implements IOutboundOrderRawItemService {
    @Autowired
    WorkOrderMapper workOrderMapper;

    @Autowired
    InboundOrderRawItemMapper inboundOrderRawItemMapper;

    @Autowired
    WorkOrderServiceImpl workOrderServiceImpl;

    @Override
    public boolean saveOrUpdate(OutboundOrderRawItem entity) {
        String workOrderNumber = entity.getWorkOrderNumber();
        QueryWrapper<WorkOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("work_order_number", workOrderNumber);
        WorkOrder workOrder = workOrderMapper.selectOne(queryWrapper);
        if (workOrder == null) {
            ExceptionCast.cast(WorkOrderCode.WORK_ORDER_NOT_EXIST);
        }
        String jsonTextWorkflow = workOrder.getJsonTextWorkflow();
        entity.setJsonTextWorkflow(jsonTextWorkflow);
        Map workflowContext = WorkflowUtil.getWorkflowContext(jsonTextWorkflow, 0);
//        entity.setCurrentOperationLabel((String) workflowContext.get("label"));
        entity.setNextOperationLabel((String) workflowContext.get("label"));
        entity.setStatus(0);
        entity.setSchCloseTime(workOrder.getSchCloseTime());

        InboundOrderRawItem inboundOrderRawItem = this.baseMapper.getStoredRawItem(entity.getProductNumber());
        if (inboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_DOES_NOT_EXIST);
        }
        if (!StringUtils.equals(workOrder.getSteelGrade(), inboundOrderRawItem.getSteelGrade())) {
            ExceptionCast.cast(OutboundOrderRawCode.MISMATCH_STEELGRADE_WITH_WORK_ORDER);
        }
        BeanUtils.copyProperties(inboundOrderRawItem, entity,
                "id", "create_time", "update_time", "create_person", "update_person");
        entity.setCustomerId(workOrder.getCustomerId());
        entity.setCustomerName(workOrder.getCustomerName());

        super.saveOrUpdate(entity);
        workOrderServiceImpl.updateWorkOrder(workOrderNumber);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectById(id);
        Integer status = outboundOrderRawItem.getStatus();
        String operationHistory = outboundOrderRawItem.getOperationHistory();
        if (status == null || status == 1 || (operationHistory != null && !"[]".equals(operationHistory))) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_NOT_REMOVE_OUTBOUND_RAE_ITEM);
        }

        super.removeById(id);
        workOrderServiceImpl.updateWorkOrder(outboundOrderRawItem.getWorkOrderNumber());
        return true;
    }

    public List<InboundOrderRawItem> getStoredRawItems(Wrapper<InboundOrderRawItem> queryWrapper) {
        List<InboundOrderRawItem> storedRawItems = this.baseMapper.getStoredRawItems(queryWrapper);
        return storedRawItems;
    }

    public OutboundOrderRawItem saveOrUpdateSplitRawItem(String productNumber, String operation, OutboundOrderRawItem entity) {
        if (entity == null) {
            ExceptionCast.cast(OutboundOrderRawCode.SPLIT_PARAMETER_ERROR);
        }
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, operation);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getStatus, 0);
        OutboundOrderRawItem parentOutboundOrderRawItem = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (parentOutboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_CHOOSE_THIS_PRODUCT_NUMBER);
        }
        String[] ignoreProperties = {"id", "outboundOrderRawId",
                "productNumber", "length", "netWeight", "grossWeight","packageWeight",
                "create_time", "update_time", "create_person", "update_person"};
        BeanUtils.copyProperties(parentOutboundOrderRawItem, entity, ignoreProperties);
/*        if (entity.getLength() == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
      Float length = parentOutboundOrderRawItem.getLength();
        if (length == null || length <= 0) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        float ratio = entity.getLength() / length;
        entity.setGrossWeight(ratio * parentOutboundOrderRawItem.getGrossWeight());
        entity.setNetWeight(ratio * parentOutboundOrderRawItem.getNetWeight());*/
        entity.setParentId(parentOutboundOrderRawItem.getId());
        entity.setTime(LocalDateTime.now());
        entity.setStatus(2);


        if (entity.getId() == null) {
            lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
            lambdaQueryWrapper.eq(OutboundOrderRawItem::getParentId, parentOutboundOrderRawItem.getId());
            Integer count = this.baseMapper.selectCount(lambdaQueryWrapper);
            entity.setProductNumber(productNumber + (char) ('A' + count));
            this.baseMapper.insert(entity);
        } else {
            OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectById(entity.getId());
            entity.setProductNumber(null);
            this.baseMapper.updateById(entity);
            entity.setProductNumber(outboundOrderRawItem.getProductNumber());
        }
        return entity;
    }

    public List<OutboundOrderRawItem> splitRawItem(String productNumber, String operation) {
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, operation);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getStatus, 0);
        OutboundOrderRawItem parentOutboundOrderRawItem = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (parentOutboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_CHOOSE_THIS_PRODUCT_NUMBER);
        }
        lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getParentId, parentOutboundOrderRawItem.getId());
        List<OutboundOrderRawItem> outboundOrderRawItems = this.baseMapper.selectList(lambdaQueryWrapper);
        if (outboundOrderRawItems == null || outboundOrderRawItems.size() == 0) {
            ExceptionCast.cast(OutboundOrderRawCode.COIL_NOT_BEEN_SPLIT);
        }
        outboundOrderRawItems.forEach(item -> {
            item.setStatus(0);
            this.baseMapper.updateById(item);
        });
        parentOutboundOrderRawItem.setStatus(1);
        this.baseMapper.updateById(parentOutboundOrderRawItem);
        return outboundOrderRawItems;
    }

    public int undoSplitRawItem(String productNumber, String operation) {
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, operation);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getStatus, 1);
        OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_UNDO_SPLIT);
        }
        lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getParentId, outboundOrderRawItem.getId());
        try {
            this.baseMapper.delete(lambdaQueryWrapper);
        } catch (Exception e) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_UNDO_SPLIT);
        }
        outboundOrderRawItem.setStatus(0);
        return this.baseMapper.updateById(outboundOrderRawItem);
    }

    public int removeSplitRawItem(Serializable id, String operation) {
        OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectById(id);
        Integer status = outboundOrderRawItem.getStatus();
        String nextOperationLabel = outboundOrderRawItem.getNextOperationLabel();
        if (status == null || status != 2 || nextOperationLabel == null || !nextOperationLabel.equals(operation)) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_NOT_REMOVE_SPLIT_COIL);
        }
        return this.baseMapper.deleteById(id);
    }

    public IPage<OutboundOrderRawItem> getOperationBoardPage(IPage<OutboundOrderRawItem> page, Wrapper<OutboundOrderRawItem> queryWrapper) {
        return this.baseMapper.getOperationBoardPage(page, queryWrapper);
    }


}
