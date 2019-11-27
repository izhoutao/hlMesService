package com.haili.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.basic.mapper.MaterialMapper;
import com.haili.basic.service.IMaterialService;
import com.haili.framework.domain.basic.Material;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-21
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {
/*    @Autowired
    MaterialTypeMapper materialTypeMapper;

*/
/*    public IPage<MaterialDto> pageMaterialDto(IPage<Material> page, Wrapper<Material> queryWrapper) {
        IPage<Material> page1 = super.page(page, queryWrapper);
        List<MaterialDto> materialDtos = page1.getRecords().stream().map(item -> {
            MaterialType materialType = materialTypeMapper.selectById(item.getTypeId());
            MaterialDto materialDto = new MaterialDto();
            BeanUtils.copyProperties(item, materialDto);
            materialDto.setTypeName(materialType.getName());
            return materialDto;
        }).collect(Collectors.toList());
        IPage<MaterialDto> page2 = new Page<>();
        BeanUtils.copyProperties(page1,page2);
        page2.setRecords(materialDtos);
        return page2;
    }*/
/*

    public IPage<Material> getMaterialDtoList(IPage<Material> page, Wrapper<Material> queryWrapper) {
        IPage<Material> materialList = getBaseMapper().getMaterialList(page, queryWrapper);
        return  materialList;
    }*/
}
