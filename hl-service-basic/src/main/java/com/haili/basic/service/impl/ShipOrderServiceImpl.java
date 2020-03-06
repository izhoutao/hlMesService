package com.haili.basic.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.OrderMapper;
import com.haili.basic.mapper.ShipOrderItemMapper;
import com.haili.basic.mapper.ShipOrderMapper;
import com.haili.basic.service.IShipOrderService;
import com.haili.framework.domain.basic.Order;
import com.haili.framework.domain.basic.ShipOrder;
import com.haili.framework.domain.basic.ShipOrderItem;
import com.haili.framework.domain.basic.response.ShipOrderCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.ModelResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-03-06
 */
@Service
public class ShipOrderServiceImpl extends ServiceImpl<ShipOrderMapper, ShipOrder> implements IShipOrderService {
    @Autowired
    UserClient userClient;

    @Autowired
    ShipOrderItemMapper shipOrderItemMapper;
    @Autowired
    OrderMapper orderMapper;
    @Override
    public boolean save(ShipOrder entity) {

        String orderNumber = entity.getOrderNumber();
        if(StringUtils.isEmpty(orderNumber)){
            LambdaQueryWrapper<Order> lambdaQueryWrapper = Wrappers.<Order>lambdaQuery();
            lambdaQueryWrapper.eq(Order::getOrderNumber, orderNumber);
            Order order = orderMapper.selectOne(lambdaQueryWrapper);
            if(order==null){
                ExceptionCast.cast(ShipOrderCode.ORDER_NOT_EXISTS);
            }
        }
        if (StringUtils.isEmpty(entity.getShipOrderNumber())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("bizName", "SHIP_ORDER");
            map.put("codeRuleName", "SHIP_ORDER");
            ModelResponseResult<String> result = userClient.nextSerialNumber(map);
            entity.setShipOrderNumber(result.getModel().toString());
        }
        entity.setStatus(0);
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        LambdaQueryWrapper<ShipOrderItem> lambdaQueryWrapper = Wrappers.<ShipOrderItem>lambdaQuery();
        lambdaQueryWrapper.eq(ShipOrderItem::getShipOrderId, id);
        return shipOrderItemMapper.delete(lambdaQueryWrapper) >= 0;
    }
}
