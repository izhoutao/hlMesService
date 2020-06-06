package com.haili.basic.controller;

import com.haili.framework.domain.basic.JournalingRewindItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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
@RestController
@RequestMapping("/basic/journalingrewinditem")
public class JournalingRewindItemController extends JournalingBaseController<JournalingRewindItem> {

    @Override
    protected String getOperation() {
        return "重卷";
    }

    @Override
    @PreAuthorize("hasAnyAuthority('rewind_split_raw_item_add','rewind_split_raw_item_update')")
    public ModelResponseResult<OutboundOrderRawItem> saveOrUpdateSplitRawItem(@PathVariable("productNumber") String productNumber, @RequestBody OutboundOrderRawItem entity) {
        return super.saveOrUpdateSplitRawItem(productNumber, entity);
    }

    @Override
    @PreAuthorize("hasAuthority('rewind_split_raw_item_submit')")
    public ModelResponseResult<List<OutboundOrderRawItem>> splitRawItem(@PathVariable("productNumber") String productNumber) {
        return super.splitRawItem(productNumber);
    }

    @Override
    @PreAuthorize("hasAuthority('rewind_split_raw_item_revoke')")
    public ResponseResult undoSplitRawItem(@PathVariable("productNumber") String productNumber) {
        return super.undoSplitRawItem(productNumber);
    }

    @Override
    @PreAuthorize("hasAuthority('rewind_split_raw_item_delete')")
    public ResponseResult removeSplitRawItem(@PathVariable("id") String id) {
        return super.removeSplitRawItem(id);
    }

    @Override
    public QueryResponseResult<JournalingRewindItem> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    public ModelResponseResult<JournalingRewindItem> save(@RequestBody JournalingRewindItem entity) {
        return super.save(entity);
    }

    @Override
    public ModelResponseResult<JournalingRewindItem> getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseResult updateById(@RequestBody JournalingRewindItem entity) {
        return super.updateById(entity);
    }

    @Override
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }

    /*
    @Autowired
    OutboundOrderRawItemServiceImpl outboundOrderRawItemServiceImpl;

    @Override
    protected QueryWrapper<JournalingRewindItem> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<JournalingRewindItem> queryWrapper = super.extractWrapperFromRequestMap(map);
        queryWrapper.gt(!StringUtils.isEmpty(map.get("journalingBeginTime")), "begin_time", map.get("journalingBeginTime"));
        queryWrapper.lt(!StringUtils.isEmpty(map.get("journalingEndTime")), "end_time", map.get("journalingEndTime"));
        return queryWrapper;
    }

    @PostMapping("/split/{productNumber}")
    @ResponseBody
    public ModelResponseResult<OutboundOrderRawItem> saveOrUpdateSplitRawItem(@PathVariable("productNumber") String productNumber, @RequestBody OutboundOrderRawItem entity) {
        outboundOrderRawItemServiceImpl.saveOrUpdateSplitRawItem(productNumber, "重卷", entity);
        return new ModelResponseResult<>(CommonCode.SUCCESS, entity);
    }

    @GetMapping("/split/{productNumber}")
    @ResponseBody
    public ModelResponseResult<List<OutboundOrderRawItem>> splitRawItem(@PathVariable("productNumber") String productNumber) {
        List<OutboundOrderRawItem> outboundOrderRawItems = outboundOrderRawItemServiceImpl.splitRawItem(productNumber, "重卷");
        return new ModelResponseResult<>(CommonCode.SUCCESS, outboundOrderRawItems);
    }

    @GetMapping("/split/undo/{productNumber}")
    @ResponseBody
    public ResponseResult undoSplitRawItem(@PathVariable("productNumber") String productNumber) {
        outboundOrderRawItemServiceImpl.undoSplitRawItem(productNumber, "重卷");
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @GetMapping("/split/delete/{id}")
    @ResponseBody
    public ResponseResult removeSplitRawItem(@PathVariable("id") String id) {
        outboundOrderRawItemServiceImpl.removeSplitRawItem(id, "重卷");
        return new ResponseResult(CommonCode.SUCCESS);
    }
*/


}
