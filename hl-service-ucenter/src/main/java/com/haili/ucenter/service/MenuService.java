package com.haili.ucenter.service;

import com.haili.framework.domain.ucenter.ext.MenuDTO;
import com.haili.ucenter.dao.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<MenuDTO> findMenuList() {

        List<MenuDTO> menuList = menuMapper.findMenuList();
        return menuList;
    }

}
