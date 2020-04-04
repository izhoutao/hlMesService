package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawDetailMapper;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.service.IInboundOrderRawItemService;
import com.haili.framework.domain.basic.InboundOrderRawDetail;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.domain.basic.response.InboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-12
 */
@Service
@Transactional
public class InboundOrderRawItemServiceImpl extends ServiceImpl<InboundOrderRawItemMapper, InboundOrderRawItem> implements IInboundOrderRawItemService {

    @Autowired
    InboundOrderRawDetailMapper inboundOrderRawDetailMapper;

    @Override
    public boolean save(InboundOrderRawItem entity) {
        String inboundOrderDetailId = entity.getInboundOrderRawDetailId();
        InboundOrderRawDetail inboundOrderRawDetail = inboundOrderRawDetailMapper.selectById(inboundOrderDetailId);
        if (inboundOrderRawDetail == null) {
            ExceptionCast.cast(InboundOrderRawCode.RAW_MATERIAL_RECEIPT_DOES_NOT_EXIST);
        }
        inboundOrderRawDetail.setReceivedQuantity(inboundOrderRawDetail.getReceivedQuantity() + 1);
        inboundOrderRawDetailMapper.updateById(inboundOrderRawDetail);
        String inboundOrderRawId = inboundOrderRawDetail.getInboundOrderRawId();
        entity.setInboundOrderRawId(inboundOrderRawId);
        return super.save(entity);
    }

    @Override
    public boolean updateById(InboundOrderRawItem entity) {
        String id = entity.getId();
        String inboundOrderRawDetailId = entity.getInboundOrderRawDetailId();
        InboundOrderRawItem inboundOrderRawItem = this.baseMapper.selectById(id);
        String inboundOrderRawDetailId1 = inboundOrderRawItem.getInboundOrderRawDetailId();
        if (!StringUtils.isEmpty(inboundOrderRawDetailId) && !inboundOrderRawDetailId.equals(inboundOrderRawDetailId1)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }

        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        InboundOrderRawItem inboundOrderRawItem = this.baseMapper.selectById(id);
        String inboundOrderRawDetailId = inboundOrderRawItem.getInboundOrderRawDetailId();
        InboundOrderRawDetail inboundOrderRawDetail = inboundOrderRawDetailMapper.selectById(inboundOrderRawDetailId);
        inboundOrderRawDetail.setReceivedQuantity(inboundOrderRawDetail.getReceivedQuantity() - 1);
        inboundOrderRawDetailMapper.updateById(inboundOrderRawDetail);
        return super.removeById(id);
    }
}
