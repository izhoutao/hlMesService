package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.DepartmentMapper;
import com.haili.basic.service.IDepartmentService;
import com.haili.framework.domain.basic.Department;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-22
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Override
    public boolean updateById(Department entity) {
        Long id = entity.getId();
        Long pid1 = entity.getPid();
        Department department = getBaseMapper().selectById(id);
        Long pid = department.getPid();
        if (id == pid1) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        if (pid != pid1) {
            Department dept = getBaseMapper().selectById(pid1);
            while (id != dept.getPid() && dept.getPid() != 0) {
                dept = getBaseMapper().selectById(dept.getPid());
            }
            if (id == dept.getPid()) {
                ExceptionCast.cast(CommonCode.INVALID_PARAM);
            }
        }
        return super.updateById(entity);
    }
}
