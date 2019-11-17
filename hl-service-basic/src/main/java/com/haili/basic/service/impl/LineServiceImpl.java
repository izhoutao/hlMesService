package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.dao.LineMapper;
import com.haili.basic.service.ILineService;
import com.haili.framework.domain.basic.Line;
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
public class LineServiceImpl extends ServiceImpl<LineMapper, Line> implements ILineService {

}
