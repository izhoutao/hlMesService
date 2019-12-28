package com.haili.ucenter.controller;

import com.haili.framework.domain.system.CodeRule;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.CodeRuleServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/sn")
    public ModelResponseResult<String> nextSerialNumber(@RequestBody Map<String, Object> map){
        String serialNumber = ((CodeRuleServiceImpl) service).nextSerialNumber(map);
        return new ModelResponseResult<String>(CommonCode.SUCCESS, serialNumber);
    }
}
