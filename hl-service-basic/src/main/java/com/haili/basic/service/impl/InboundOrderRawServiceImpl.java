package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.InboundOrderRawDetailMapper;
import com.haili.basic.mapper.InboundOrderRawItemMapper;
import com.haili.basic.mapper.InboundOrderRawMapper;
import com.haili.basic.service.IInboundOrderRawService;
import com.haili.framework.domain.basic.InboundOrderRaw;
import com.haili.framework.domain.basic.InboundOrderRawDetail;
import com.haili.framework.domain.basic.InboundOrderRawItem;
import com.haili.framework.model.response.ModelResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

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
public class InboundOrderRawServiceImpl extends ServiceImpl<InboundOrderRawMapper, InboundOrderRaw> implements IInboundOrderRawService {
    @Autowired
    UserClient userClient;
    @Autowired
    InboundOrderRawDetailMapper inboundOrderRawDetailMapper;
    @Autowired
    InboundOrderRawItemMapper inboundOrderRawItemMapper;
    @Override
    public boolean save(InboundOrderRaw entity) {
        if (StringUtils.isEmpty(entity.getNumber())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("bizName", "RAW_MATERIAL_INBOUND_ORDER");
            map.put("codeRuleName", "RAW_MATERIAL_INBOUND_ORDER");
            ModelResponseResult<String> result = userClient.nextSerialNumber(map);
            entity.setNumber(result.getModel().toString());
        }
//        entity.setStatusId("1197053003799048194");
        return super.save(entity);
    }


/*    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<InboundOrderRawDetail> lambdaQueryWrapper = Wrappers.<InboundOrderRawDetail>lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderRawDetail::getInboundOrderId, id);
        List<InboundOrderRawDetail> inboundOrderRawDetails = inboundOrderRawDetailMapper.selectList(lambdaQueryWrapper);
        List<String> inboundOrderRawDetailIdList = inboundOrderRawDetails.stream().map(item -> item.getId()).collect(Collectors.toList());
        inboundOrderRawDetailMapper.delete(lambdaQueryWrapper);
        LambdaQueryWrapper<InboundOrderRawItem> lambdaQueryWrapper1 = Wrappers.<InboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper1.in(InboundOrderRawItem::getInboundOrderRawId, inboundOrderRawDetailIdList);
        return inboundOrderRawItemMapper.delete(lambdaQueryWrapper1) >= 0;
    }*/

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<InboundOrderRawDetail> lambdaQueryWrapper = Wrappers.<InboundOrderRawDetail>lambdaQuery();
        lambdaQueryWrapper.eq(InboundOrderRawDetail::getInboundOrderRawId, id);
        inboundOrderRawDetailMapper.delete(lambdaQueryWrapper);
        LambdaQueryWrapper<InboundOrderRawItem> lambdaQueryWrapper1 = Wrappers.<InboundOrderRawItem>lambdaQuery();
        lambdaQueryWrapper1.eq(InboundOrderRawItem::getInboundOrderRawId, id);
        return inboundOrderRawItemMapper.delete(lambdaQueryWrapper1) >= 0;
    }

}
