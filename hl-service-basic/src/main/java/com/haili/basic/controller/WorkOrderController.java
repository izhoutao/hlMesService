package com.haili.basic.controller;

import cn.hutool.core.util.NumberUtil;
import com.haili.basic.client.UserClient;
import com.haili.framework.domain.basic.WorkOrder;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

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


}
