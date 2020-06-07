package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.OutboundOrderRawDetailMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.mapper.WorkOrderMapper;
import com.haili.basic.service.IOutboundOrderRawItemService;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.OutboundOrderRawDetail;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.WorkOrder;
import com.haili.framework.domain.basic.response.OutboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.utils.WorkflowUtil;
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
    OutboundOrderRawDetailMapper outboundOrderRawDetailMapper;

    @Autowired
    InboundOrderRawItemMapper inboundOrderRawItemMapper;

    @Override
    public boolean save(OutboundOrderRawItem entity) {
        String outboundOrderDetailId = entity.getOutboundOrderRawDetailId();
        OutboundOrderRawDetail outboundOrderRawDetail = outboundOrderRawDetailMapper.selectById(outboundOrderDetailId);
        if (outboundOrderRawDetail == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_RECEIPT_DOES_NOT_EXIST);
        }
        outboundOrderRawDetail.setOutQuantity(outboundOrderRawDetail.getOutQuantity() + 1);
        outboundOrderRawDetailMapper.updateById(outboundOrderRawDetail);
        InboundOrderRawItem inboundOrderRawItem = this.baseMapper.getStoredRawItem(entity.getProductNumber());
        if (inboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_DOES_NOT_EXIST);
        }
        BeanUtils.copyProperties(inboundOrderRawItem, entity, "id");
        String outboundOrderRawId = outboundOrderRawDetail.getOutboundOrderRawId();
        entity.setOutboundOrderRawId(outboundOrderRawId);
        String workOrderNumber = outboundOrderRawDetail.getWorkOrderNumber();
        entity.setWorkOrderNumber(workOrderNumber);

        QueryWrapper<WorkOrder> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("work_order_number", workOrderNumber);
        WorkOrder workOrder = workOrderMapper.selectOne(queryWrapper1);
        String jsonTextWorkflow = workOrder.getJsonTextWorkflow();
        entity.setJsonTextWorkflow(jsonTextWorkflow);
        Map workflowContext = WorkflowUtil.getWorkflowContext(jsonTextWorkflow, 0);
//        entity.setCurrentOperationLabel((String) workflowContext.get("label"));
        entity.setNextOperationLabel((String) workflowContext.get("label"));
        entity.setStatus(0);
        return super.save(entity);
    }

    @Override
    public boolean updateById(OutboundOrderRawItem entity) {
        String id = entity.getId();
        InboundOrderRawItem inboundOrderRawItem = this.baseMapper.getStoredRawItem(entity.getProductNumber());
        if (inboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_DOES_NOT_EXIST);
        }
        OutboundOrderRawItem outboundOrderRawItem = new OutboundOrderRawItem().setId(id)
                .setProductNumber(entity.getProductNumber())
                .setTime(entity.getTime());
        BeanUtils.copyProperties(inboundOrderRawItem, outboundOrderRawItem, "id");
        return super.updateById(outboundOrderRawItem);
    }

    @Override
    public boolean removeById(Serializable id) {
        OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectById(id);
        Integer status = outboundOrderRawItem.getStatus();
        String operationHistory = outboundOrderRawItem.getOperationHistory();
        if (status == null || status == 1 || (operationHistory != null && !"[]".equals(operationHistory))) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_NOT_REMOVE_OUTBOUND_RAE_ITEM);
        }
        String outboundOrderRawDetailId = outboundOrderRawItem.getOutboundOrderRawDetailId();
        OutboundOrderRawDetail outboundOrderRawDetail = outboundOrderRawDetailMapper.selectById(outboundOrderRawDetailId);
        outboundOrderRawDetail.setOutQuantity(outboundOrderRawDetail.getOutQuantity() - 1);
        outboundOrderRawDetailMapper.updateById(outboundOrderRawDetail);
        return super.removeById(id);
    }

    @Override
    public List<String> getStoredRawItems() {
        List<String> storedRawItems = this.baseMapper.getStoredRawItems();
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
        String[] ignoreProperties = {"id", "outboundOrderRawDetailId", "outboundOrderRawId",
                "productNumber", "length", "netWeight", "grossWeight", "barcode"};
        BeanUtils.copyProperties(parentOutboundOrderRawItem, entity, ignoreProperties);
        if (entity.getLength() == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Float length = parentOutboundOrderRawItem.getLength();
        if (length == null || length <= 0) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
/*        float ratio = entity.getLength() / length;
        entity.setGrossWeight(ratio * parentOutboundOrderRawItem.getGrossWeight());
        entity.setNetWeight(ratio * parentOutboundOrderRawItem.getNetWeight());*/
        entity.setParentId(parentOutboundOrderRawItem.getId());
        entity.setTime(LocalDateTime.now());
        entity.setStatus(2);

        entity.setOutboundOrderRawDetailId(null);
        entity.setOutboundOrderRawId(null);

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
        /*        boolean isAnyChildSplit = false;
        for (int i = 0; i < outboundOrderRawItems.size(); i++) {
            if (outboundOrderRawItems.get(i).getStatus() == 1) {
                isAnyChildSplit = true;
                break;
            }
        }
        if (isAnyChildSplit) {
            ExceptionCast.cast(OutboundOrderRawCode.EXIST_SUBVOLUME_BEEN_USED_OR_DIVIDED);
        }*/
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

    /*
    public List<OutboundOrderRawItem> splitRawItems(String productNumber, String operation, List<OutboundOrderRawItem> list) {
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, operation);

        OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.CANNOT_CHOOSE_THIS_PRODUCT_NUMBER);
        }

        if (list == null) {
            ExceptionCast.cast(OutboundOrderRawCode.SPLIT_PARAMETER_ERROR);
        }
        Float length = outboundOrderRawItem.getLength();
        if (length == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Float length1 = 0F;
        for (int i = 0; i < list.size(); i++) {
            OutboundOrderRawItem outboundOrderRawItem1 = list.get(i);
            String[] ignoreProperties = {"id", "length", "netWeight", "grossWeight", "barcode"};
            BeanUtils.copyProperties(outboundOrderRawItem, outboundOrderRawItem1, ignoreProperties);
            outboundOrderRawItem1.setId(null);
            outboundOrderRawItem1.setProductNumber(productNumber + (char) ('A' + i));
            if (outboundOrderRawItem1.getLength() == null) {
                ExceptionCast.cast(CommonCode.INVALID_PARAM);
            }
            float ratio = outboundOrderRawItem1.getLength() / length;
            outboundOrderRawItem1.setGrossWeight(ratio * outboundOrderRawItem1.getGrossWeight());
            outboundOrderRawItem1.setNetWeight(ratio * outboundOrderRawItem1.getNetWeight());
            outboundOrderRawItem1.setParentId(outboundOrderRawItem.getId());
            outboundOrderRawItem1.setStatus(0);

            length1 += outboundOrderRawItem1.getLength();
        }
        if (length == null || NumberUtil.compare(length, length1) != 0) {
            ExceptionCast.cast(OutboundOrderRawCode.SPLIT_PARAMETER_ERROR);
        }
        outboundOrderRawItem.setStatus(1);
        this.baseMapper.updateById(outboundOrderRawItem);
        saveBatch(list);
        return list;
    }
*/
}
