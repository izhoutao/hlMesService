package com.haili.ucenter.controller;

import com.haili.framework.domain.system.Sequence;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.SequenceServiceImpl;
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
 * @since 2019-12-28
 */
@RestController
@RequestMapping("/ucenter/sequence")
public class SequenceController extends CrudController<Sequence> {
    @PostMapping("/sn")
    public ModelResponseResult<String> nextSerialNumber(@RequestBody Map<String, Object> map) {
        String serialNumber = ((SequenceServiceImpl) service).nextSerialNumber(map);
        return new ModelResponseResult<String>(CommonCode.SUCCESS, serialNumber);
    }

    @PostMapping("/sn/{num}")
    public ModelResponseResult<List<String>> getSerialNumberList(@PathVariable("num") String num, @RequestBody Map<String, Object> map) {
        List<String> serialNumberList = ((SequenceServiceImpl) service).getSerialNumberList(num, map);
        return new ModelResponseResult<List<String>>(CommonCode.SUCCESS, serialNumberList);
    }

    @Override
    @PreAuthorize("hasRole('admin')")
    public QueryResponseResult<Sequence> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasRole('admin')")
    public ModelResponseResult<Sequence> save(@RequestBody Sequence entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasRole('admin')")
    public ResponseResult updateById(@RequestBody Sequence entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasRole('admin')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
