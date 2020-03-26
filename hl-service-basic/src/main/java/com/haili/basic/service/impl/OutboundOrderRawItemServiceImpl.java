package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.*;
import com.haili.basic.service.IOutboundOrderRawItemService;
import com.haili.framework.domain.basic.*;
import com.haili.framework.domain.basic.response.OutboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

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
    WorkflowMapper workflowMapper;
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
        QueryWrapper<InboundOrderRawItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_number", entity.getProductNumber());
        InboundOrderRawItem inboundOrderRawItem = inboundOrderRawItemMapper.selectOne(queryWrapper);
        if (inboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_DOES_NOT_EXIST);
        }
        BeanUtils.copyProperties(inboundOrderRawItem, entity);
        String outboundOrderRawId = outboundOrderRawDetail.getOutboundOrderRawId();
        entity.setOutboundOrderRawId(outboundOrderRawId);
        String workOrderNumber = outboundOrderRawDetail.getWorkOrderNumber();
        entity.setWorkOrderNumber(workOrderNumber);

        QueryWrapper<WorkOrder> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("work_order_number", entity.getProductNumber());
        WorkOrder workOrder = workOrderMapper.selectOne(queryWrapper1);
        String jsonTextWorkflow = workOrder.getJsonTextWorkflow();
        entity.setJsonTextWorkflow(jsonTextWorkflow);
        return super.save(entity);
    }

    @Override
    public boolean updateById(OutboundOrderRawItem entity) {
        OutboundOrderRawItem outboundOrderRawItem = this.baseMapper.selectById(entity.getId());
        QueryWrapper<OutboundOrderRawItem> outboundOrderRawItemQueryWrapper = new QueryWrapper<>();
        outboundOrderRawItemQueryWrapper.eq("outbound_order_raw_detail_id", outboundOrderRawItem.getOutboundOrderRawDetailId());
        OutboundOrderRawItem outboundOrderRawItem1 = this.baseMapper.selectOne(outboundOrderRawItemQueryWrapper);
        if (outboundOrderRawItem == null || outboundOrderRawItem1 == null || StringUtils.isEmpty(outboundOrderRawItem.getOutboundOrderRawDetailId())
                || StringUtils.isEmpty(outboundOrderRawItem1.getOutboundOrderRawDetailId())
                || !outboundOrderRawItem.getOutboundOrderRawDetailId().equals(outboundOrderRawItem1.getOutboundOrderRawDetailId())) {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        String outboundOrderDetailId = entity.getOutboundOrderRawDetailId();
        OutboundOrderRawDetail outboundOrderRawDetail = outboundOrderRawDetailMapper.selectById(outboundOrderDetailId);
        if (outboundOrderRawDetail == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_RECEIPT_DOES_NOT_EXIST);
        }
        QueryWrapper<InboundOrderRawItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_number", entity.getProductNumber());
        InboundOrderRawItem inboundOrderRawItem = inboundOrderRawItemMapper.selectOne(queryWrapper);
        if (inboundOrderRawItem == null) {
            ExceptionCast.cast(OutboundOrderRawCode.RAW_MATERIAL_DOES_NOT_EXIST);
        }
        BeanUtils.copyProperties(inboundOrderRawItem, entity);
        String outboundOrderRawId = outboundOrderRawDetail.getOutboundOrderRawId();
        entity.setOutboundOrderRawId(outboundOrderRawId);
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
