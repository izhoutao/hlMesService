package com.haili.basic.controller;

import com.haili.framework.domain.basic.Department;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.web.CrudController;
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
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/basic/department")
public class DepartmentController extends CrudController<Department> {
    @Override
    public QueryResponseResult<Department> list(@RequestBody Map<String, Object> map) {
        QueryResponseResult<Department> queryResponseResult = super.list(map);
        queryResponseResult.getQueryResult().getList().removeIf(item -> item.getId() == 0);
        return queryResponseResult;
    }
}
