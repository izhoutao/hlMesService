package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderRawDetailMapper;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.InboundOrderRawMapper;
import com.haili.basic.service.IInboundOrderRawDetailService;
import com.haili.framework.domain.basic.InboundOrderRaw;
import com.haili.framework.domain.basic.InboundOrderRawDetail;
import com.haili.framework.domain.basic.response.InboundOrderRawCode;
import com.haili.framework.exception.ExceptionCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class InboundOrderRawDetailServiceImpl extends ServiceImpl<InboundOrderRawDetailMapper, InboundOrderRawDetail> implements IInboundOrderRawDetailService {
    @Autowired
    private InboundOrderRawMapper inboundOrderRawMapper;
    @Autowired
    private InboundOrderRawItemMapper inboundOrderRawItemMapper;

    @Override
    public IPage<InboundOrderRawDetail> page(IPage<InboundOrderRawDetail> page, Wrapper<InboundOrderRawDetail> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<InboundOrderRawDetail> list(Wrapper<InboundOrderRawDetail> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }

    @Override
    public boolean save(InboundOrderRawDetail entity) {
        String inboundOrderRawId = entity.getInboundOrderRawId();
        InboundOrderRaw inboundOrderRaw = inboundOrderRawMapper.selectById(inboundOrderRawId);
        if (inboundOrderRaw == null) {
            ExceptionCast.cast(InboundOrderRawCode.RAW_MATERIAL_RECEIPT_DOES_NOT_EXIST);
        }
        if (entity.getReceivedQuantity() == null) {
            entity.setReceivedQuantity(0);
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(InboundOrderRawDetail entity) {
        entity.setInboundOrderRawId(null);
        entity.setReceivedQuantity(null);
        return super.updateById(entity);
    }

/*    @Override
    public boolean removeById(Serializable id) {
        LambdaQueryWrapper<InboundOrderRawItem> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderRawItem::getInboundOrderRawDetailId, id);
        inboundOrderRawItemMapper.delete(lambdaQueryWrapper);
        return super.removeById(id);
    }*/
}
