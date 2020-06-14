package com.haili.basic.controller;

import com.haili.basic.service.impl.QcDefectServiceImpl;
import com.haili.framework.domain.basic.QcDefect;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/basic/qcdefect")
public class QcDefectController extends CrudController<QcDefect> {
    @Override
    public ModelResponseResult<QcDefect> save(@RequestBody @Valid QcDefect entity) {
        return super.save(entity);
    }

    @Override
    public ResponseResult updateById(@RequestBody @Valid QcDefect entity) {
        return super.updateById(entity);
    }

/*    @PostMapping
    @ResponseBody
    public ModelResponseResult<List<QcDefect>> saveBatch(@RequestBody List<QcDefect> qcDefects) {
        service.saveBatch(qcDefects);
        return new ModelResponseResult<List<QcDefect>>(CommonCode.SUCCESS, qcDefects);
    }*/


    @GetMapping("/top/{num}")
    public ModelResponseResult<List<QcDefect>> getTopDefects(@PathVariable("num") Integer num) {
        List<QcDefect> topDefects = ((QcDefectServiceImpl) service).getTopDefects(num);
        return new ModelResponseResult<>(CommonCode.SUCCESS, topDefects);
    }


}
