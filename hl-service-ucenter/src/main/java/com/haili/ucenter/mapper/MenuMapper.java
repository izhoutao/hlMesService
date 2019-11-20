package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.ext.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    public List<MenuDTO> findMenuList();

}
