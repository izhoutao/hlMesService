package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.OrderMapper;
import com.haili.basic.service.IOrderService;
import com.haili.framework.domain.basic.Order;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
//    @Autowired
//    @Qualifier("orderNumberGenerator")
//    private AbstractSerialNumberGenerator orderNumberGenerator;

    @Autowired
    UserClient userClient;

    @Override
    public boolean save(Order entity) {
        if (StringUtils.isEmpty(entity.getOrderNumber())) {
//            entity.setOrderNumber(orderNumberGenerator.nextSerialNumber("NO|[yyyy]|[MM]|[dd]|[SEQ]|", 4));
            HashMap<String, Object> map = new HashMap<>();
            map.put("bizName", "orderNumber");
            map.put("id", "1203236378603696129");
            map.put("length", 4);
            map.put("resetValue", "0001");
            ModelResponseResult<String> result = userClient.nextSerialNumber(map);
            entity.setOrderNumber(result.getModel().toString());
        }
        return super.save(entity);
    }
}
