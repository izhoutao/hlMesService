package com.haili.basic.controller;

import com.haili.framework.domain.basic.Printer;
import com.haili.framework.model.response.ModelResponseResult;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-03
*/
@RestController
@RequestMapping("/basic/printer")
public class PrinterController extends CrudController<Printer> {
    @Override
//    @PreAuthorize("hasAuthority('printer_list')")
    public QueryResponseResult<Printer> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('printer_save')")
    public ModelResponseResult<Printer> save(@RequestBody Printer entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('printer_update')")
    public ResponseResult updateById(@RequestBody Printer entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('printer_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
