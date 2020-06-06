package com.haili.basic.controller;

import com.haili.basic.service.impl.OutboundOrderRawItemServiceImpl;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.model.response.*;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    @Override
    public QueryResponseResult<OutboundOrderRawItem> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('outboundRaw')")
    public ModelResponseResult<OutboundOrderRawItem> save(@RequestBody OutboundOrderRawItem entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outboundRaw')")
    public ModelResponseResult<OutboundOrderRawItem> getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('outboundRaw')")
    public ResponseResult updateById(@RequestBody OutboundOrderRawItem entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outboundRaw')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }

    @GetMapping("/stored")
    @ResponseBody
    @PreAuthorize("hasAuthority('outboundRaw')")
    public QueryResponseResult<String> getStoredRawItems() {
        List<String> storedRawItems = ((OutboundOrderRawItemServiceImpl) service).getStoredRawItems();
        QueryResult<String> queryResult = new QueryResult<>();
        queryResult.setList(storedRawItems);
        queryResult.setTotal(storedRawItems.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
