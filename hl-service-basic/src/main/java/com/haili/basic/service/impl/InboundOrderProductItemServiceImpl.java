package com.haili.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.InboundOrderProductItemMapper;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.InboundOrderRawMapper;
import com.haili.basic.mapper.OutboundOrderRawItemMapper;
import com.haili.basic.service.IInboundOrderProductItemService;
import com.haili.framework.domain.basic.InboundOrderProductItem;
import com.haili.framework.domain.basic.OutboundOrderRawItem;
import com.haili.framework.domain.basic.response.IpqcCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-05-30
 */
@Service
public class InboundOrderProductItemServiceImpl extends ServiceImpl<InboundOrderProductItemMapper, InboundOrderProductItem> implements IInboundOrderProductItemService {

    /*    @Override
    public IPage<JournalingRewindItem> page(IPage<JournalingRewindItem> page, Wrapper<JournalingRewindItem> queryWrapper) {
        return this.baseMapper.selectPagePreload(page, queryWrapper);
    }

    @Override
    public List<JournalingRewindItem> list(Wrapper<JournalingRewindItem> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }*/
    @Autowired
    InboundOrderRawItemMapper inboundOrderRawItemMapper;
    @Autowired
    InboundOrderRawMapper inboundOrderRawMapper;
    @Autowired
    OutboundOrderRawItemMapper outboundOrderRawItemMapper;

    @Override
    public boolean save(InboundOrderProductItem entity) {
        super.save(entity);
        String productNumber = entity.getProductNumber();
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, "成品入库");
        OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(IpqcCode.IPQC_INSPECTOR_RESULT_CANNOT_BE_MODIFIED);
        }
        outboundOrderRawItem.setCurrentOperationLabel("成品入库");
        outboundOrderRawItem.setNextOperationLabel("");
        outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
        return true;
    }

    @Override
    public boolean updateById(InboundOrderProductItem entity) {
        String id = entity.getId();
        InboundOrderProductItem inboundOrderProductItem = this.baseMapper.selectById(id);
        if (inboundOrderProductItem == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }

        String productNumber = inboundOrderProductItem.getProductNumber();
        if (!productNumber.equals(entity.getProductNumber())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        InboundOrderProductItem inboundOrderProductItem = this.baseMapper.selectById(id);
        String productNumber = inboundOrderProductItem.getProductNumber();
        LambdaQueryWrapper<OutboundOrderRawItem> lambdaQueryWrapper = Wrappers.<OutboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getProductNumber, productNumber);
        lambdaQueryWrapper.eq(OutboundOrderRawItem::getNextOperationLabel, "");
        OutboundOrderRawItem outboundOrderRawItem = outboundOrderRawItemMapper.selectOne(lambdaQueryWrapper);
        if (outboundOrderRawItem == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        String operationHistory = outboundOrderRawItem.getOperationHistory();
        List<Map> maps = JSON.parseArray(operationHistory, Map.class);
        int size = maps.size();
        String previousOperation;
        if (size == 0) {
            previousOperation = "";
        } else {
            previousOperation = (String) (maps.get(size - 1).get("operationName"));
        }
        outboundOrderRawItem.setCurrentOperationLabel(previousOperation);
        outboundOrderRawItem.setNextOperationLabel("成品入库");
        outboundOrderRawItemMapper.updateById(outboundOrderRawItem);
        return super.removeById(id);
    }

}
