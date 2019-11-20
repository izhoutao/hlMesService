package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.basic.Vendor;
import com.haili.basic.mapper.VendorMapper;
import com.haili.basic.service.IVendorService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商信息表 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-20
 */
@Service
public class VendorServiceImpl extends ServiceImpl<VendorMapper, Vendor> implements IVendorService {

}
