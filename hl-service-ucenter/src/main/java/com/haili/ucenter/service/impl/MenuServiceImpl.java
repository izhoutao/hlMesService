package com.haili.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.Permission;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.ucenter.mapper.MenuMapper;
import com.haili.ucenter.mapper.PermissionMapper;
import com.haili.ucenter.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-24
 */
@Service
@Transactional
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Menu> listPreload(Wrapper<Menu> queryWrapper) {
        return this.baseMapper.selectListPreload(queryWrapper);
    }

    @Override
    public List<Serializable> getDeleteMenus(List<Menu> menuList, List<Serializable> idList) {
        // 递归找出待删除的菜单
        for (Menu menu : menuList) {
            idList.add(menu.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("pid", menu.getId());
            List<Menu> menus = this.baseMapper.selectByMap(map);
            if (menus != null && menus.size() != 0) {
                getDeleteMenus(menus, idList);
            }
        }
        return idList;
    }

/*
    @Override
    public boolean updateById(Menu entity) {
        String id = entity.getId();
        String pid1 = entity.getPid();
        Menu menu = this.baseMapper.selectById(id);
        String pid = menu.getPid();
        if (id == pid1) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        if (StringUtils.isNotBlank(pid1) && pid1 != "0" && pid != pid1) {
            Menu m = this.baseMapper.selectById(pid1);
            if (m != null) {
                String pid2 = m.getPid();
                while (pid2 != "0" && id != pid2) {
                    pid2 = this.baseMapper.selectById(pid2).getPid();
                }
                if (id == pid2) {
                    ExceptionCast.cast(CommonCode.INVALID_PARAM);
                }
            }
        }
        return super.updateById(entity);
    }
*/

    @Override
    public boolean updateById(Menu entity) {
        String id = entity.getId();
        String pid1 = entity.getPid();
        if (pid1 != null) {
            String pid = this.baseMapper.selectById(id).getId();
            if (id == pid1) {
                ExceptionCast.cast(CommonCode.INVALID_PARAM);
            }
            if (pid != pid1 && pid1 != "0") {
                Menu m = null;
                do {
                    m = this.baseMapper.selectById(pid1);
                } while (m != null && (pid1 = m.getPid()) != null && pid1 != "0" && id != pid1);
                if (id == pid1) {
                    ExceptionCast.cast(CommonCode.INVALID_PARAM);
                }
            }
        }
        return super.updateById(entity);
    }


    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        queryWrapper.in("menu_id", idList);
        permissionMapper.delete(queryWrapper);
        return super.removeByIds(idList);
    }
}
