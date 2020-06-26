package com.haili.ucenter.controller;

import com.haili.framework.domain.system.CodeRule;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.SequenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-07
 */
@RestController
@RequestMapping("/ucenter/coderule")
public class CodeRuleController extends CrudController<CodeRule> {
    @Autowired
    SequenceServiceImpl sequenceService;

    @PostMapping("/sn")
    public ModelResponseResult<String> nextSerialNumber(@RequestBody Map<String, Object> map) {
        String serialNumber = sequenceService.nextSerialNumber(map);
        return new ModelResponseResult<String>(CommonCode.SUCCESS, serialNumber);
    }

    @Override
//    @PreAuthorize("hasAuthority('code_rule_list')")
    public QueryResponseResult<CodeRule> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('code_rule_save')")
    public ModelResponseResult<CodeRule> save(@RequestBody CodeRule entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('code_rule_update')")
    public ResponseResult updateById(@RequestBody CodeRule entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('code_rule_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
