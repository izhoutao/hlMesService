package com.haili.basic.controller;

import com.haili.framework.domain.basic.JournalingAnnealItem;
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
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/basic/journalingannealitem")
public class JournalingAnnealItemController extends JournalingBaseController<JournalingAnnealItem> {
    @Override
    protected String getOperation() {
        return "退火炉";
    }

    @Override
    @PreAuthorize("hasAnyAuthority('anneal_split_raw_item_add','anneal_split_raw_item_update')")
    public ModelResponseResult<OutboundOrderRawItem> saveOrUpdateSplitRawItem(@PathVariable("productNumber") String productNumber, @RequestBody OutboundOrderRawItem entity) {
        return super.saveOrUpdateSplitRawItem(productNumber, entity);
    }

    @Override
    @PreAuthorize("hasAuthority('anneal_split_raw_item_submit')")
    public ModelResponseResult<List<OutboundOrderRawItem>> splitRawItem(@PathVariable("productNumber") String productNumber) {
        return super.splitRawItem(productNumber);
    }

    @Override
    @PreAuthorize("hasAuthority('anneal_split_raw_item_revoke')")
    public ResponseResult undoSplitRawItem(@PathVariable("productNumber") String productNumber) {
        return super.undoSplitRawItem(productNumber);
    }

    @Override
    @PreAuthorize("hasAuthority('anneal_split_raw_item_delete')")
    public ResponseResult removeSplitRawItem(@PathVariable("id") String id) {
        return super.removeSplitRawItem(id);
    }

    @Override
//    @PreAuthorize("hasAuthority('journaling_anneal_item_list')")
    public QueryResponseResult<JournalingAnnealItem> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_anneal_item_save')")
    public ModelResponseResult<JournalingAnnealItem> save(@RequestBody JournalingAnnealItem entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_anneal_item_update')")
    public ResponseResult updateById(@RequestBody JournalingAnnealItem entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('journaling_anneal_item_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }

}
