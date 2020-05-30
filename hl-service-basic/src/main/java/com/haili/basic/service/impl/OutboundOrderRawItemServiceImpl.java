package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.util.StringUtils;

import java.io.Serializable;
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
        return super.save(entity);
    }

    @Override
    public boolean updateById(OutboundOrderRawItem entity) {
        String id = entity.getId();
        String outboundOrderRawDetailId = entity.getOutboundOrderRawDetailId();
        OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectById(id);
        String outboundOrderRawDetailId1 = outboundOrderRawItem.getOutboundOrderRawDetailId();
        if (!StringUtils.isEmpty(outboundOrderRawDetailId) && !outboundOrderRawDetailId.equals(outboundOrderRawDetailId1)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        InboundOrderRawItem inboundOrderRawItem = this.baseMapper.getStoredRawItem(entity.getProductNumber());
        if (inboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_DOES_NOT_EXIST);
        }
        OutboundOrderRawItem outboundOrderRawItem2 = new OutboundOrderRawItem().setId(id)
                .setProductNumber(entity.getProductNumber())
                .setTime(entity.getTime());
        BeanUtils.copyProperties(inboundOrderRawItem, outboundOrderRawItem2, "id");
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectById(id);
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
}
