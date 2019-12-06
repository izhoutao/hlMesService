package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.*;
import com.haili.basic.service.ILineStationService;
import com.haili.framework.domain.basic.LineStation;
import com.haili.framework.domain.basic.LineStationPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-04
 */
@Service
@Transactional
public class LineStationServiceImpl extends ServiceImpl<LineStationMapper, LineStation> implements ILineStationService {
//    @Autowired
//    LineMapper lineMapper;
//    @Autowired
//    OperationMapper operationMapper;
//    @Autowired
//    PrinterMapper printerMapper;
    @Autowired
    LineStationPrinterMapper lineStationPrinterMapper;

//    @Override
//    public IPage<LineStation> page(IPage<LineStation> page, Wrapper<LineStation> queryWrapper) {
//        IPage<LineStation> lineStationPage = super.page(page, queryWrapper);
//        List<LineStation> lineStationList = getLineStationsPreload(lineStationPage.getRecords());
//        lineStationPage.setRecords(lineStationList);
//        return lineStationPage;
//    }
//
//    @Override
//    public List<LineStation> list(Wrapper<LineStation> queryWrapper) {
//        List<LineStation> lineStationList = super.list(queryWrapper);
//        return getLineStationsPreload(lineStationList);
//    }
//
//    private List<LineStation> getLineStationsPreload(List<LineStation> lineStationList) {
//        return lineStationList.stream().map(lineStation -> {
//            String lineId = lineStation.getLineId();
//            String operationId = lineStation.getOperationId();
//            Line line = lineMapper.selectById(lineId);
//            Operation operation = operationMapper.selectById(operationId);
//            lineStation.setLineName(line.getName());
//            lineStation.setOperationName(operation.getName());
//            return lineStation;
//        }).collect(Collectors.toList());
//    }

    @Override
    public boolean removeById(Serializable id) {
        LineStation lineStation = this.baseMapper.selectById(id);
        LambdaQueryWrapper<LineStationPrinter> lineStationPrinterLambdaQueryWrapper = Wrappers.<LineStationPrinter>lambdaQuery();
        lineStationPrinterLambdaQueryWrapper.in(LineStationPrinter::getLineStationId, lineStation.getId());
        lineStationPrinterMapper.delete(lineStationPrinterLambdaQueryWrapper);
        return super.removeById(id);
    }

}
