package com.haili.basic.controller;

import com.haili.basic.service.impl.OutboundOrderRawItemServiceImpl;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.QueryResult;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-19
 */
@RestController
@RequestMapping("/basic/outboundorderrawitem")
public class OutboundOrderRawItemController extends CrudController<OutboundOrderRawItem> {
    @GetMapping("/stored")
    @ResponseBody
    public QueryResponseResult<String> getStoredRawItems() {
        List<String> storedRawItems = ((OutboundOrderRawItemServiceImpl) service).getStoredRawItems();
        QueryResult<String> queryResult = new QueryResult<>();
        queryResult.setList(storedRawItems);
        queryResult.setTotal(storedRawItems.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
