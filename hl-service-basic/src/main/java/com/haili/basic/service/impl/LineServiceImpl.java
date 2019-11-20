package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.basic.Line;
import com.haili.basic.mapper.LineMapper;
import com.haili.basic.service.ILineService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-18
 */
@Service
public class LineServiceImpl extends ServiceImpl<LineMapper, Line> implements ILineService {

}
