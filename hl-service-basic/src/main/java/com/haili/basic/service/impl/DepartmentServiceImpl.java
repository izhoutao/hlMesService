package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.DepartmentMapper;
import com.haili.basic.service.IDepartmentService;
import com.haili.framework.domain.basic.Department;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-22
 */
@Service
@Transactional
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
/*    @Override
    public boolean updateById(Department entity) {
        Long id = entity.getId();
        Long pid1 = entity.getPid();
        Department department = this.baseMapper.selectById(id);
        Long pid = department.getPid();
        if (id == pid1) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        if (pid1 != null && pid1 != 0 && pid != pid1) {
            Department dept = this.baseMapper.selectById(pid1);
            if (dept != null) {
                Long pid2 = dept.getPid();
                while (pid2 != 0 && id != pid2) {
                    pid2 = this.baseMapper.selectById(pid2).getPid();
                }
                if (id == pid2) {
                    ExceptionCast.cast(CommonCode.INVALID_PARAM);
                }
            }
        }
        return super.updateById(entity);
    }*/

    @Override
    public boolean updateById(Department entity) {
        Long id = entity.getId();
        Long pid1 = entity.getPid();
        if (pid1 != null) {
            Long pid = this.baseMapper.selectById(id).getId();
            if (id == pid1) {
                ExceptionCast.cast(CommonCode.INVALID_PARAM);
            }
            if (pid != pid1 && pid1 != 0) {
                Department dept = null;
                do {
                    dept = this.baseMapper.selectById(pid1);
                } while (dept != null && (pid1 = dept.getPid()) != null && pid1 != 0 && id != pid1);
                if (id == pid1) {
                    ExceptionCast.cast(CommonCode.INVALID_PARAM);
                }
            }
        }
        return super.updateById(entity);
    }


    @Override
    public List<Serializable> getDeleteDepartments(List<Department> departmentList, List<Serializable> idList) {
        // 递归找出待删除的部门
        for (Department department : departmentList) {
            idList.add(department.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("pid", department.getId());
            List<Department> departments = this.baseMapper.selectByMap(map);
            if (departments != null && departments.size() != 0) {
                getDeleteDepartments(departments, idList);
            }
        }
        return idList;
    }

}
