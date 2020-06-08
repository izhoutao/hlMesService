package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.JournalingGrindItemMapper;
import com.haili.basic.service.IJournalingGrindItemService;
import com.haili.framework.domain.basic.JournalingGrindItem;
import com.haili.framework.domain.basic.response.JournalingShiftReportCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-08
 */
@Service
public class JournalingGrindItemServiceImpl extends ServiceImpl<JournalingGrindItemMapper, JournalingGrindItem> implements IJournalingGrindItemService {
    @Override
    public boolean updateById(JournalingGrindItem entity) {
        String id = entity.getId();
        JournalingGrindItem journalingGrindItem = this.baseMapper.selectById(id);
        if (journalingGrindItem == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Integer status = journalingGrindItem.getStatus();
        if (status != 0) {
            ExceptionCast.cast(JournalingShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_MODIFY);
        }

        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        JournalingGrindItem journalingGrindItem = this.baseMapper.selectById(id);
        Integer status = journalingGrindItem.getStatus();
        if (status != 0) {
            ExceptionCast.cast(JournalingShiftReportCode.JOURNALING_ITEM_ALREADY_APPROVED_AND_CANNOT_DELETE);
        }
        return super.removeById(id);
    }
}
