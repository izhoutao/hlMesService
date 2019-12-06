package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.LineStationPrinterMapper;
import com.haili.basic.service.ILineStationPrinterService;
import com.haili.framework.domain.basic.LineStationPrinter;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-04
 */
@Service
public class LineStationPrinterServiceImpl extends ServiceImpl<LineStationPrinterMapper, LineStationPrinter> implements ILineStationPrinterService {
//    @Autowired
//    PrinterMapper printerMapper;
//
//    @Override
//    public IPage<LineStationPrinter> page(IPage<LineStationPrinter> page, Wrapper<LineStationPrinter> queryWrapper) {
//        IPage<LineStationPrinter> page1 = super.page(page, queryWrapper);
//        List<LineStationPrinter> lineStationPrinterList = page1.getRecords().stream().map(lineStationPrinter -> {
//            String printerId = lineStationPrinter.getPrinterId();
//            Printer printer = printerMapper.selectById(printerId);
//            lineStationPrinter.setPrinterName(printer.getName());
//            lineStationPrinter.setPrinterPath(printer.getPath());
//            return lineStationPrinter;
//        }).collect(Collectors.toList());
//        page1.setRecords(lineStationPrinterList);
//        return page1;
//    }
//
//    @Override
//    public List<LineStationPrinter> list(Wrapper<LineStationPrinter> queryWrapper) {
//        List<LineStationPrinter> lineStationPrinterList = super.list(queryWrapper);
//        lineStationPrinterList = lineStationPrinterList.stream().map(lineStationPrinter -> {
//            String printerId = lineStationPrinter.getPrinterId();
//            Printer printer = printerMapper.selectById(printerId);
//            lineStationPrinter.setPrinterName(printer.getName());
//            lineStationPrinter.setPrinterPath(printer.getPath());
//            return lineStationPrinter;
//        }).collect(Collectors.toList());
//        return lineStationPrinterList;
//    }

    @Override
    public boolean updateById(LineStationPrinter entity) {
        if (entity.getLineStationId() != null && entity.getPrinterId() != null && entity.getIsDefault() != null) {
            UpdateWrapper<LineStationPrinter> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("line_station_id", entity.getLineStationId()).set("is_default", false);
            this.baseMapper.update(null, updateWrapper);
            UpdateWrapper<LineStationPrinter> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.eq("line_station_id", entity.getLineStationId()).eq("printer_id", entity.getPrinterId()).set("is_default", true);
            this.baseMapper.update(null, updateWrapper1);
            return true;
        } else {
            return super.updateById(entity);
        }
    }
}
