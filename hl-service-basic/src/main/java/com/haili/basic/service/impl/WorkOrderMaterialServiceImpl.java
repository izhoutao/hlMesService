package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.WorkOrderMaterialMapper;
import com.haili.basic.service.IWorkOrderMaterialService;
import com.haili.framework.domain.basic.WorkOrderMaterial;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-02-27
 */
@Service
public class WorkOrderMaterialServiceImpl extends ServiceImpl<WorkOrderMaterialMapper, WorkOrderMaterial> implements IWorkOrderMaterialService {
    @Override
    public boolean save(WorkOrderMaterial entity) {
        entity.setStatus(0);
        entity.setUsedNum(0);
        return super.save(entity);
    }
}
