package com.haili.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.system.DictInfo;
import com.haili.framework.domain.system.DictType;
import com.haili.ucenter.mapper.DictInfoMapper;
import com.haili.ucenter.mapper.DictTypeMapper;
import com.haili.ucenter.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-17
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {
    @Autowired
    DictInfoMapper dictInfoMapper;

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        QueryWrapper<DictInfo> dictInfoQueryWrapper = new QueryWrapper<>();
        dictInfoQueryWrapper.eq("type_id", id);
        return dictInfoMapper.delete(dictInfoQueryWrapper) >= 0;
    }
}
