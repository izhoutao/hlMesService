package com.haili.basic.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.haili.framework.domain.basic.Material;
import com.haili.framework.domain.basic.dto.MaterialDto;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-21
 */
public interface MaterialMapper extends BaseMapper<Material> {

    IPage<MaterialDto> getMaterialDtoList(IPage<Material> page, @Param(Constants.WRAPPER) Wrapper<Material> queryWrapper);

}
