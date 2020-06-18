package com.haili.basic.controller;

import com.haili.framework.domain.basic.Customer;
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
 * 供应商信息表 控制器
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-02
 */
@RestController
@RequestMapping("/basic/customer")
public class CustomerController extends CrudController<Customer> {
    @Override
//    @PreAuthorize("hasAuthority('customer_list')")
    public QueryResponseResult<Customer> list(@RequestBody Map<String, Object> map) {
        return super.list(map);
    }

    @Override
    @PreAuthorize("hasAuthority('customer_save')")
    public ModelResponseResult<Customer> save(@RequestBody Customer entity) {
        return super.save(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('customer_update')")
    public ResponseResult updateById(@RequestBody Customer entity) {
        return super.updateById(entity);
    }

    @Override
    @PreAuthorize("hasAuthority('customer_delete')")
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        return super.deleteById(id);
    }
}
