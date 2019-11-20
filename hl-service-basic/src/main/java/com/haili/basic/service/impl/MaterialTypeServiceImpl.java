package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.basic.MaterialType;
import com.haili.basic.mapper.MaterialTypeMapper;
import com.haili.basic.service.IMaterialTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-20
 */
@Service
public class MaterialTypeServiceImpl extends ServiceImpl<MaterialTypeMapper, MaterialType> implements IMaterialTypeService {

}
