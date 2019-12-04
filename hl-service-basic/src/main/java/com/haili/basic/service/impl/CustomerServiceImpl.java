package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.CustomerMapper;
import com.haili.basic.service.ICustomerService;
import com.haili.framework.domain.basic.Customer;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商信息表 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-02
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
