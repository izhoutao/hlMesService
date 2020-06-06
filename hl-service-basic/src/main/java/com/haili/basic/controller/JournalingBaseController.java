package com.haili.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.basic.service.impl.OutboundOrderRawItemServiceImpl;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
 */
public abstract class JournalingBaseController<T> extends CrudController<T> {
     protected abstract String getOperation();
    @Autowired
    OutboundOrderRawItemServiceImpl outboundOrderRawItemServiceImpl;

    @Override
    protected QueryWrapper<T> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<T> queryWrapper = super.extractWrapperFromRequestMap(map);
        queryWrapper.gt(!StringUtils.isEmpty(map.get("journalingBeginTime")), "begin_time", map.get("journalingBeginTime"));
        queryWrapper.lt(!StringUtils.isEmpty(map.get("journalingEndTime")), "end_time", map.get("journalingEndTime"));
        return queryWrapper;
    }

    @PostMapping("/split/{productNumber}")
    @ResponseBody
    public ModelResponseResult<OutboundOrderRawItem> saveOrUpdateSplitRawItem(@PathVariable("productNumber") String productNumber, @RequestBody OutboundOrderRawItem entity) {
        outboundOrderRawItemServiceImpl.saveOrUpdateSplitRawItem(productNumber, getOperation(), entity);
        return new ModelResponseResult<>(CommonCode.SUCCESS, entity);
    }

    @GetMapping("/split/{productNumber}")
    @ResponseBody
    public ModelResponseResult<List<OutboundOrderRawItem>> splitRawItem(@PathVariable("productNumber") String productNumber) {
        List<OutboundOrderRawItem> outboundOrderRawItems = outboundOrderRawItemServiceImpl.splitRawItem(productNumber, getOperation());
        return new ModelResponseResult<>(CommonCode.SUCCESS, outboundOrderRawItems);
    }

    @GetMapping("/split/undo/{productNumber}")
    @ResponseBody
    public ResponseResult undoSplitRawItem(@PathVariable("productNumber") String productNumber) {
        outboundOrderRawItemServiceImpl.undoSplitRawItem(productNumber, getOperation());
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/split/delete/{id}")
    @ResponseBody
    public ResponseResult removeSplitRawItem(@PathVariable("id") String id) {
        outboundOrderRawItemServiceImpl.removeSplitRawItem(id, getOperation());
        return new ResponseResult(CommonCode.SUCCESS);
    }


}
