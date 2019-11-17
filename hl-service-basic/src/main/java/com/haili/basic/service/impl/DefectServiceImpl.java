package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.basic.Defect;
import com.haili.basic.dao.DefectMapper;
import com.haili.basic.service.IDefectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-15
 */
@Service
public class DefectServiceImpl extends ServiceImpl<DefectMapper, Defect> implements IDefectService {

}
