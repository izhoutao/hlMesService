package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.QcDefectMapper;
import com.haili.basic.service.IQcDefectService;
import com.haili.framework.domain.basic.QcDefect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-04-02
 */
@Service
public class QcDefectServiceImpl extends ServiceImpl<QcDefectMapper, QcDefect> implements IQcDefectService {
/*    @Autowired
    IpqcMapper ipqcMapper;

    @Override
    public boolean save(QcDefect entity) {
        String ipqcId = entity.getIpqcId();
        Ipqc ipqc = ipqcMapper.selectById(ipqcId);
        if (ipqc == null) {
            ExceptionCast.cast(QcDefectCode.QC_DEFECT_IPQC_NOT_EXIST);
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(QcDefect entity) {
        String ipqcId = entity.getIpqcId();
        Ipqc ipqc = ipqcMapper.selectById(ipqcId);
        if(!StringUtils.isEmpty(ipqcId)&& !ipqcId.equals(ipqc.getId())){
            ExceptionCast.cast(QcDefectCode.QC_DEFECT_IPQC_CANNOT_CHANGE);
        }
        return super.updateById(entity);
    }*/


    public List<QcDefect> getTopDefects(Integer num) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstday = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime lastDay = now.with(TemporalAdjusters.lastDayOfMonth());
        List<QcDefect> topDefects = this.baseMapper.getTopDefects(firstday.toLocalDate(), lastDay.toLocalDate(), num);
        return topDefects;
    }
}
