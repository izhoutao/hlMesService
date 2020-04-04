package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.basic.QcDefect;
import com.haili.basic.mapper.QcDefectMapper;
import com.haili.basic.service.IQcDefectService;
import org.springframework.stereotype.Service;

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
    @Override
    public boolean save(QcDefect entity) {
        return super.save(entity);
    }

}
