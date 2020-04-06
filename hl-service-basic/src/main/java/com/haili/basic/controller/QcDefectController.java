package com.haili.basic.controller;

import com.haili.framework.domain.basic.QcDefect;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  控制器
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
}
