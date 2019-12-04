package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.basic.Printer;
import com.haili.basic.mapper.PrinterMapper;
import com.haili.basic.service.IPrinterService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-03
 */
@Service
public class PrinterServiceImpl extends ServiceImpl<PrinterMapper, Printer> implements IPrinterService {

}
