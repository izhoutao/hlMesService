package com.haili.basic.controller;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.basic.client.UserClient;
import com.haili.basic.service.impl.WorkOrderServiceImpl;
import com.haili.framework.domain.basic.WorkOrder;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-02-27
 */
@RestController
@RequestMapping("/basic/workorder")
public class WorkOrderController extends CrudController<WorkOrder> {
    @Autowired
    UserClient userClient;


    @Override
//    @PreAuthorize("hasAuthority('work_order_list')")
    public QueryResponseResult<WorkOrder> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('work_order_save')")
    public ModelResponseResult<WorkOrder> save(@RequestBody WorkOrder entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('work_order_update')")
    public ResponseResult updateById(@RequestBody WorkOrder entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('work_order_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }

    @GetMapping("/psn/{num}")
    public ModelResponseResult<List<String>> getProductSerialNumbers(@PathVariable("num") String num) {
        if (!NumberUtil.isInteger(num)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("bizName", "WORK_ORDER_PRODUCT");
        map.put("codeRuleName", "WORK_ORDER_PRODUCT");
//        ModelResponseResult<List<String>> result = userClient.getSerialNumberList(num, map);
        return userClient.getSerialNumberList(num, map);
    }


    @GetMapping("/completion")
    public ModelResponseResult<Map> getCompletionBasicInfos() {
        Map workOrderBasicInfos = ((WorkOrderServiceImpl) service).getCompletionBasicInfos();
        return new ModelResponseResult<>(CommonCode.SUCCESS, workOrderBasicInfos);
    }

    @GetMapping("/completion/{numberOfMonth}")
    public ModelResponseResult<Map> getMonthUnpunctualWorkOrderQuantity(@PathVariable("numberOfMonth") Integer numberOfMonth) {
        Map<String, List> map = ((WorkOrderServiceImpl) service).getMonthUnpunctualWorkOrderQuantity(numberOfMonth);
        return new ModelResponseResult<>(CommonCode.SUCCESS, map);
    }

    @GetMapping("/basic")
    public ModelResponseResult<Map> getWorkOrderBasicInfos() {
        Map workOrderBasicInfos = ((WorkOrderServiceImpl) service).getWorkOrderBasicInfos();
        return new ModelResponseResult<>(CommonCode.SUCCESS, workOrderBasicInfos);
    }

    @Override
    protected QueryWrapper<WorkOrder> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<WorkOrder> queryWrapper = super.extractWrapperFromRequestMap(map);
        queryWrapper.eq(!StringUtils.isEmpty(map.get("productNumber")), "product_number", map.get("productNumber"));
        queryWrapper.eq(!StringUtils.isEmpty(map.get("status")), "status", map.get("status"));
        Object dateRange = map.get("dateRange");
        if (dateRange instanceof List && dateRange != null && ((List) dateRange).size() == 2) {
            queryWrapper.gt(!StringUtils.isEmpty(map.get("dateRange")), "date", ((List) dateRange).get(0));
            queryWrapper.lt(!StringUtils.isEmpty(map.get("dateRange")), "date", ((List) dateRange).get(1));
        }
        return queryWrapper;
    }
}
