package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haili.basic.service.impl.OutboundOrderRawItemServiceImpl;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.model.response.*;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
//    @PreAuthorize("hasAuthority('outbound_order_raw_item_list')")
    public QueryResponseResult<OutboundOrderRawItem> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @PostMapping("/operationboard")
    @ResponseBody
    public QueryResponseResult<OutboundOrderRawItem> getOperationBoardPage(@RequestBody Map<String, Object> map) {
        Page<OutboundOrderRawItem> page = extractPageFromRequestMap(map);
        LambdaQueryWrapper<OutboundOrderRawItem> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.apply("o.status=0").ne(OutboundOrderRawItem::getNextOperationLabel, "");
        IPage<OutboundOrderRawItem> page1 = ((OutboundOrderRawItemServiceImpl) service).getOperationBoardPage(page, queryWrapper);
        QueryResult<OutboundOrderRawItem> queryResult = new QueryResult<>();
        queryResult.setList(page1.getRecords());
        queryResult.setTotal(page1.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_save')")
    public ModelResponseResult<OutboundOrderRawItem> save(@RequestBody @Valid OutboundOrderRawItem entity) {
        service.saveOrUpdate(entity);
        return new ModelResponseResult<>(CommonCode.SUCCESS, entity);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_update')")
    public ResponseResult updateById(@RequestBody @Valid OutboundOrderRawItem entity) {
        service.saveOrUpdate(entity);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @PreAuthorize("hasAuthority('outbound_order_raw_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }

    @PostMapping("/stored")
    @ResponseBody
    public QueryResponseResult<InboundOrderRawItem> getStoredRawItems(@RequestBody Map<String, Object> map ) {
        String steelGrade = (String)map.get("steelGrade");
        QueryWrapper<InboundOrderRawItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(steelGrade),"inbound.steel_grade",steelGrade);
        List<InboundOrderRawItem> storedRawItems = ((OutboundOrderRawItemServiceImpl) service).getStoredRawItems(queryWrapper);
        QueryResult<InboundOrderRawItem> queryResult = new QueryResult<>();
        queryResult.setList(storedRawItems);
        queryResult.setTotal(storedRawItems.size());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
