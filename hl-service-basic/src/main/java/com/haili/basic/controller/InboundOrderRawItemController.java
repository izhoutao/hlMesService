package com.haili.basic.controller;

import cn.hutool.core.util.NumberUtil;
import com.haili.basic.client.UserClient;
import com.haili.basic.service.impl.InboundOrderRawItemServiceImpl;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-12
*/
@RestController
@RequestMapping("/basic/inboundorderrawitem")
public class InboundOrderRawItemController extends CrudController<InboundOrderRawItem> {
    @Autowired
    UserClient userClient;

    @GetMapping("/psn/{codeRule}/{num}")
    public ModelResponseResult<List<String>> getProductSerialNumbers(@PathVariable("codeRule") String codeRule,@PathVariable("num") String num) {
        if (!NumberUtil.isInteger(num)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("bizName", codeRule);
        map.put("codeRuleName", codeRule);
//        ModelResponseResult<List<String>> result = userClient.getSerialNumberList(num, map);
        return userClient.getSerialNumberList(num, map);
    }


    @GetMapping("/outboundrawitemproductnumber/{productNumber}")
    @ResponseBody
    public ModelResponseResult<InboundOrderRawItem> getByOutboundRawItemProductNumber(@PathVariable("productNumber") String productNumber) {
        InboundOrderRawItem obj = ((InboundOrderRawItemServiceImpl)service).getByOutboundRawItemProductNumber(productNumber);
        return new ModelResponseResult<>(CommonCode.SUCCESS, obj);
    }

    @Override
//    @PreAuthorize("hasAuthority('inbound_order_raw_list')")
    public QueryResponseResult<InboundOrderRawItem> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('inbound_order_raw_save')")
    public ModelResponseResult<InboundOrderRawItem> save(@RequestBody InboundOrderRawItem entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('inbound_order_raw_update')")
    public ResponseResult updateById(@RequestBody InboundOrderRawItem entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('inbound_order_raw_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
