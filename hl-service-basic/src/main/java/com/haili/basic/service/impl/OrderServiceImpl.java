package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.OrderItemMapper;
import com.haili.basic.mapper.OrderMapper;
import com.haili.basic.service.IOrderService;
import com.haili.framework.domain.basic.Order;
import com.haili.framework.domain.basic.OrderItem;
import com.haili.framework.model.response.ModelResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
//    @Autowired
//    @Qualifier("orderNumberGenerator")
//    private AbstractSerialNumberGenerator orderNumberGenerator;

    @Autowired
    UserClient userClient;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Override
    public boolean save(Order entity) {
        if (StringUtils.isEmpty(entity.getOrderNumber())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("bizName", "ORDER");
            map.put("codeRuleName", "ORDER");
            ModelResponseResult<String> result = userClient.nextSerialNumber(map);
            entity.setOrderNumber(result.getModel().toString());
        }
        entity.setStatus(0);
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<OrderItem> lambdaQueryWrapper = Wrappers.<OrderItem>lambdaQuery();
        lambdaQueryWrapper.eq(OrderItem::getOrderId, id);
        return orderItemMapper.delete(lambdaQueryWrapper) >= 0;
    }
}
