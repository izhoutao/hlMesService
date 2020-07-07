package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.InboundOrderProductItemMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.mapper.WorkOrderMapper;
import com.haili.basic.mapper.WorkflowMapper;
import com.haili.basic.service.IWorkOrderService;
import com.haili.framework.domain.basic.InboundOrderProductItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.WorkOrder;
import com.haili.framework.domain.basic.Workflow;
import com.haili.framework.domain.basic.response.WorkOrderCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (entity.getStatus() == new Integer(1)
                || entity.getStatus() == new Integer(2)) {
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
        Integer status = 2;
        int size = outboundOrderRawItems.size();
        if (size == 0) {
            status = null;
        } else {
            for (int i = 0; i < size; i++) {
                OutboundOrderRawItem item = outboundOrderRawItems.get(i);
                if (item.getStatus() == 0 && !"".equals(item.getNextOperationLabel())) {
                    status = 1;
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
                .set(status != null, WorkOrder::getStatus, status)
                .set(status == new Integer(2), WorkOrder::getCloseTime, LocalDateTime.now());

        return this.baseMapper.update(null, lambdaUpdateWrapper);
    }

    public Map getCompletionBasicInfos() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstday = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime lastDay = now.with(TemporalAdjusters.lastDayOfMonth());

        LambdaQueryWrapper<WorkOrder> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper.lt(WorkOrder::getSchCloseTime, now);
        lambdaQueryWrapper.eq(WorkOrder::getStatus, 1);
//        lambdaQueryWrapper.gt(WorkOrder::getSchCloseTime, firstday);
        List<WorkOrder> workOrders = this.baseMapper.selectList(lambdaQueryWrapper);
        Integer incompleteWorkOrderQuantity = workOrders.size();
//        List<String> workOrderNumbers = workOrders.stream().map(WorkOrder::getWorkOrderNumber).collect(Collectors.toList());

        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper1 = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper1.eq(OutboundOrderRawItem::getStatus, 0);
        lambdaQueryWrapper1.ne(OutboundOrderRawItem::getNextOperationLabel, "");
        Float incompleteWeight = outboundOrderRawItemMapper.selectList(lambdaQueryWrapper1)
                .stream().map(OutboundOrderRawItem::getGrossWeight).reduce(Float::sum).orElse(0f);

        LambdaQueryWrapper<InboundOrderProductItem> lambdaQueryWrapper2 = Wrappers.lambdaQuery();
        lambdaQueryWrapper2.gt(InboundOrderProductItem::getDate, firstday);
        lambdaQueryWrapper2.lt(InboundOrderProductItem::getDate, lastDay);
        lambdaQueryWrapper2.select(InboundOrderProductItem::getGrossWeight);
        Float currentMonthInboundFinishedProductWeight = inboundOrderProductItemMapper.selectList(lambdaQueryWrapper2)
                .stream().map(InboundOrderProductItem::getGrossWeight).reduce(Float::sum).orElse(0f);


        LambdaQueryWrapper<WorkOrder> lambdaQueryWrapper3 = Wrappers.lambdaQuery();
//        lambdaQueryWrapper3.apply("sch_close_time > close_time");
        lambdaQueryWrapper3.gt(WorkOrder::getSchCloseTime, firstday);
        lambdaQueryWrapper3.lt(WorkOrder::getSchCloseTime, lastDay);
        List<WorkOrder> workOrders1 = this.baseMapper.selectList(lambdaQueryWrapper3);
        long onTimeCompletionCount = workOrders1.stream().filter(wo -> wo.getCloseTime() != null && wo.getSchCloseTime().isAfter(wo.getCloseTime())).count();
        Double onTimeCompletionRate = onTimeCompletionCount * 1.0 / workOrders1.size();

        Map map = new HashMap<>();
        map.put("incompleteWorkOrderQuantity", incompleteWorkOrderQuantity);
        map.put("incompleteWeight", incompleteWeight);
        map.put("currentMonthInboundFinishedProductWeight", currentMonthInboundFinishedProductWeight);
        map.put("onTimeCompletionRate", onTimeCompletionRate);
        return map;

    }

    public Map<String, List> getMonthUnpunctualWorkOrderQuantity(int numberOfMonth) {
        LocalDateTime now = LocalDateTime.now();
        List<Integer> monthUnpunctualQuantitys = new ArrayList<>(numberOfMonth);
        List<Integer> monthPunctualQuantitys = new ArrayList<>(numberOfMonth);
        List<Double> rates = new ArrayList<>(numberOfMonth);
        for (int i = numberOfMonth; i > 0; i--) {
            LocalDateTime fd = now.minusMonths(i).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
            LocalDateTime ld = now.minusMonths(i).with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
            LambdaQueryWrapper<WorkOrder> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.ge(WorkOrder::getSchCloseTime, fd);
            lambdaQueryWrapper.le(WorkOrder::getSchCloseTime, ld);
            List<WorkOrder> workOrders = this.baseMapper.selectList(lambdaQueryWrapper);
            int monthTotal = workOrders.size();
            int monthUnpunctualQuantity = (int) workOrders.stream().filter(wo -> (wo.getCloseTime() == null || wo.getCloseTime().isAfter(wo.getSchCloseTime()))).count();
            int monthPunctualQuantity = monthTotal - monthUnpunctualQuantity;
            double rate = monthTotal != 0 ? (double) monthPunctualQuantity / monthTotal : 0;
            monthUnpunctualQuantitys.add(monthUnpunctualQuantity);
            monthPunctualQuantitys.add(monthPunctualQuantity);
            rates.add(rate);
        }
        HashMap<String, List> map = new HashMap<>();
        map.put("punctuals", monthPunctualQuantitys);
        map.put("unpunctuals", monthUnpunctualQuantitys);
        map.put("rates", rates);
        return map;
    }

    public Map getWorkOrderBasicInfos() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fd = now.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        LocalDateTime ld = now.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        LambdaQueryWrapper<WorkOrder> qw1 = Wrappers.lambdaQuery();
        qw1.ge(WorkOrder::getCreateTime, fd);
        qw1.le(WorkOrder::getCreateTime, ld);
        List<WorkOrder> workOrders = this.baseMapper.selectList(qw1);
        int newWorkOrderCountOfThisMonth = workOrders.size();
        Float newWorkOrderWeightOfThisMonth = 0f;
        Float newWorkOrderCompletedWeightOfThisMonth = 0f;

        for (int i = 0; i < newWorkOrderCountOfThisMonth; i++) {
            WorkOrder workOrder = workOrders.get(i);
            newWorkOrderWeightOfThisMonth += workOrder.getNum();
            newWorkOrderCompletedWeightOfThisMonth += workOrder.getOutputNum();
        }


        LambdaQueryWrapper<WorkOrder> qw2 = Wrappers.lambdaQuery();
        LocalDateTime de = now.with(LocalTime.MAX);
        qw2.eq(WorkOrder::getStatus, 1).le(WorkOrder::getSchStartTime, de);
        workOrders = this.baseMapper.selectList(qw2);
        int todayWorkOrderCount = workOrders.size();
        Float todayWorkOrderWeight = 0f;
        Float todayWorkOrderOnlineWeight = 0f;

        for (int i = 0; i < todayWorkOrderCount; i++) {
            WorkOrder workOrder = workOrders.get(i);
            todayWorkOrderWeight += workOrder.getNum();
            todayWorkOrderOnlineWeight += workOrder.getNum() - workOrder.getOutputNum();
        }


        LambdaQueryWrapper<WorkOrder> qw3 = Wrappers.lambdaQuery();
        de = now.plusDays(6).with(LocalTime.MAX);
        qw2.eq(WorkOrder::getStatus, 1).le(WorkOrder::getSchStartTime, de);
        workOrders = this.baseMapper.selectList(qw2);
        int future7daysWorkOrderCount = workOrders.size();
        Float future7daysWorkOrderWeight = 0f;
        Float future7daysWorkOrderOnlineWeight = 0f;

        for (int i = 0; i < future7daysWorkOrderCount; i++) {
            WorkOrder workOrder = workOrders.get(i);
            future7daysWorkOrderWeight += workOrder.getNum();
            future7daysWorkOrderOnlineWeight += workOrder.getNum() - workOrder.getOutputNum();
        }
        Map map = new HashMap<>();
        map.put("newWorkOrderCountOfThisMonth", newWorkOrderCountOfThisMonth);
        map.put("newWorkOrderWeightOfThisMonth", newWorkOrderWeightOfThisMonth);
        map.put("newWorkOrderCompletedWeightOfThisMonth", newWorkOrderCompletedWeightOfThisMonth);
        map.put("todayWorkOrderCount", todayWorkOrderCount);
        map.put("todayWorkOrderWeight", todayWorkOrderWeight);
        map.put("todayWorkOrderOnlineWeight", todayWorkOrderOnlineWeight);
        map.put("future7daysWorkOrderCount", future7daysWorkOrderCount);
        map.put("future7daysWorkOrderWeight", future7daysWorkOrderWeight);
        map.put("future7daysWorkOrderOnlineWeight", future7daysWorkOrderOnlineWeight);
        return map;

    }

}
