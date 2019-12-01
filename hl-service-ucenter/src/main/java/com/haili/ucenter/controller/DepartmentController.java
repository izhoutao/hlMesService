package com.haili.ucenter.controller;

import com.haili.framework.domain.basic.Department;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.ResponseResult;
import com.haili.framework.web.CrudController;
import com.haili.ucenter.service.impl.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/ucenter/department")
public class DepartmentController extends CrudController<Department> {
    @Override
    public ResponseResult deleteById(@PathVariable("id") Serializable id) {
        Map<String, Object> map = new HashMap<>();
        map.put("pid", id);
        List<Department> departmentList = (List<Department>) this.getService().listByMap(map);
        List<Serializable> idList = new ArrayList<>();
        idList.add(id);
        idList = ((DepartmentServiceImpl) this.getService()).getDeleteDepartments(departmentList, idList);
        this.getService().removeByIds(idList);
        return new ResponseResult(CommonCode.SUCCESS);

    }
}
