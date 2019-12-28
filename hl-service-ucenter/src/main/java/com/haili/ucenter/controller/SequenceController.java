package com.haili.ucenter.controller;

import com.haili.framework.domain.system.Sequence;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.SequenceServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  控制器
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
        String serialNumber = ((SequenceServiceImpl)service).nextSerialNumber(map);
        return new ModelResponseResult<String>(CommonCode.SUCCESS, serialNumber);
    }
}
