package com.haili.basic.controller;

import cn.hutool.core.util.NumberUtil;
import com.haili.basic.client.UserClient;
import com.haili.basic.service.impl.InboundOrderRawItemServiceImpl;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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



}
