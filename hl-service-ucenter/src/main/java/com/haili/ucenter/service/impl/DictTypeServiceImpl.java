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
import java.util.*;

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


    public Map<String, List<DictInfo>> getDictMaps(List<String> dictTypeIds) {
        HashMap<String, List<DictInfo>> map = new HashMap<>();
        QueryWrapper<DictInfo> dictInfoQueryWrapper = new QueryWrapper<>();
        dictInfoQueryWrapper.in("type_id", dictTypeIds);
        List<DictInfo> dictInfoList = dictInfoMapper.selectList(dictInfoQueryWrapper);
        dictInfoList.stream().forEach(dictInfo -> {
            String typeId = dictInfo.getTypeId();
            if (map.get(typeId) == null) {
                LinkedList<DictInfo> dictInfos = new LinkedList<>();
                dictInfos.add(dictInfo);
                map.put(typeId,dictInfos);
            } else {
                map.get(typeId).add(dictInfo);
            }
        });
        return map;
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        QueryWrapper<DictInfo> dictInfoQueryWrapper = new QueryWrapper<>();
        dictInfoQueryWrapper.eq("type_id", id);
        return dictInfoMapper.delete(dictInfoQueryWrapper) >= 0;
    }
}
