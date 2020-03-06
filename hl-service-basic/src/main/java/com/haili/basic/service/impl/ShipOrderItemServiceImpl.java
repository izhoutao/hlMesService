package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.client.UserClient;
import com.haili.basic.mapper.ShipOrderItemMapper;
import com.haili.basic.service.IShipOrderItemService;
import com.haili.framework.domain.basic.ShipOrderItem;
import com.haili.framework.model.response.ModelResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class ShipOrderItemServiceImpl extends ServiceImpl<ShipOrderItemMapper, ShipOrderItem> implements IShipOrderItemService {
    @Autowired
    UserClient userClient;
    @Override
    public boolean save(ShipOrderItem entity) {
        if (StringUtils.isEmpty(entity.getShipOrderItemNumber())) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("bizName", "SHIP_ORDER_ITEM");
            map.put("codeRuleName", "SHIP_ORDER_ITEM");
            ModelResponseResult<String> result = userClient.nextSerialNumber(map);
            entity.setShipOrderItemNumber(result.getModel().toString());
        }
        entity.setStatus(0);
        entity.setShippedNum(0);
        return super.save(entity);
    }
}
