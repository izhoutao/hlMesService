package com.haili.ucenter.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haili.framework.domain.ucenter.Menu;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-24
 */
public interface IMenuService extends IService<Menu> {
    public List<Menu> listPreload(Wrapper<Menu> queryWrapper);
    public  List<Serializable>  getDeleteMenus(List<Menu> menuList, List<Serializable> idList);
}
