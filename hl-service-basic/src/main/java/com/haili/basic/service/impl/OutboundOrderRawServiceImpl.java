package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.OutboundOrderRawDetailMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.mapper.OutboundOrderRawMapper;
import com.haili.basic.mapper.WorkOrderMapper;
import com.haili.basic.service.IOutboundOrderRawService;
import com.haili.framework.domain.basic.OutboundOrderRaw;
import com.haili.framework.domain.basic.OutboundOrderRawDetail;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.WorkOrder;
import com.haili.framework.domain.basic.response.OutboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
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
 * @since 2020-03-19
 */
@Service
@Transactional
public class OutboundOrderRawServiceImpl extends ServiceImpl<OutboundOrderRawMapper, OutboundOrderRaw> implements IOutboundOrderRawService {
    @Autowired
    UserClient userClient;
    @Autowired
    WorkOrderMapper workOrderMapper;
    @Autowired
    OutboundOrderRawDetailMapper outboundOrderRawDetailMapper;
    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;

    @Override
    public boolean save(OutboundOrderRaw entity) {
        String workOrderNumber = entity.getWorkOrderNumber();
        if (StringUtils.isEmpty(workOrderNumber)) {
            ExceptionCast.cast(OutboundOrderRawCode.WORK_ORDER_DOES_NOT_EXIST);
        }
        QueryWrapper<WorkOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("work_order_number", workOrderNumber);
        WorkOrder workOrder = workOrderMapper.selectOne(wrapper);
        if (workOrder == null) {
            ExceptionCast.cast(OutboundOrderRawCode.WORK_ORDER_DOES_NOT_EXIST);

        }
        if (StringUtils.isEmpty(entity.getNumber())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("bizName", "RAW_MATERIAL_OUTBOUND_ORDER");
            map.put("codeRuleName", "RAW_MATERIAL_OUTBOUND_ORDER");
            ModelResponseResult<String> result = userClient.nextSerialNumber(map);
            entity.setNumber(result.getModel().toString());
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(OutboundOrderRaw entity) {
        String id = entity.getId();
        String workOrderNumber = entity.getWorkOrderNumber();
        if (!StringUtils.isEmpty(workOrderNumber)) {
            OutboundOrderRaw outboundOrderRaw = this.baseMapper.selectById(id);
            String workOrderNumber1 = outboundOrderRaw.getWorkOrderNumber();
            if(!"1197053003799048194".equals(outboundOrderRaw.getStatusId())){
                ExceptionCast.cast(OutboundOrderRawCode.CANNOT_EDIT_NON_NEWLY_BUILT_OUTBOUND_ORDER_RAW);
            }
            if (!workOrderNumber.equals(workOrderNumber1)) {
                QueryWrapper<OutboundOrderRawItem> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("outbound_order_raw_id", id);
                Integer count = outboundOrderRawItemMapper.selectCount(queryWrapper);
                if (count > 0) {
                    ExceptionCast.cast(OutboundOrderRawCode.WORK_ORDER_HAS_RAW_ITEM_OUT);
                }
            }
        }
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        LambdaQueryWrapper<OutboundOrderRawDetail> lambdaQueryWrapper = Wrappers.<OutboundOrderRawDetail>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawDetail::getOutboundOrderRawId, id);
        outboundOrderRawDetailMapper.delete(lambdaQueryWrapper);
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper1 = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper1.eq(OutboundOrderRawItem::getOutboundOrderRawId, id);
        outboundOrderRawItemMapper.delete(lambdaQueryWrapper1);
        return super.removeById(id);
    }
}
