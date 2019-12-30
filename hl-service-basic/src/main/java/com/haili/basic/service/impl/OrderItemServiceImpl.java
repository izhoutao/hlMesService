package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.OrderItemMapper;
import com.haili.basic.service.IOrderItemService;
import com.haili.framework.domain.basic.OrderItem;
import com.haili.framework.model.response.ModelResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {
    @Autowired
    UserClient userClient;

    @Override
    public boolean save(OrderItem entity) {
        if (StringUtils.isEmpty(entity.getOrderItemNumber())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("bizName", "ORDER_ITEM");
            map.put("codeRuleName", "ORDER_ITEM");
            ModelResponseResult<String> result = userClient.nextSerialNumber(map);
            entity.setOrderItemNumber(result.getModel().toString());
        }
        return super.save(entity);
    }
}
