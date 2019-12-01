package com.haili.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haili.framework.domain.basic.Department;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-22
 */
public interface IDepartmentService extends IService<Department> {
    public List<Serializable> getDeleteDepartments(List<Department> departmentList, List<Serializable> idList);
}
