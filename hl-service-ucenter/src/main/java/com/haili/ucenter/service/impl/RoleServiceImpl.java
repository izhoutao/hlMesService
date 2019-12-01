package com.haili.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.Permission;
import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.ucenter.mapper.PermissionMapper;
import com.haili.ucenter.mapper.RoleMapper;
import com.haili.ucenter.mapper.UserRoleMapper;
import com.haili.ucenter.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    PermissionMapper permissionMapper;
    /*    @Autowired
        MenuMapper menuMapper;*/
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<Role> list(Wrapper<Role> queryWrapper) {
        return this.baseMapper.selectListPreLoad(queryWrapper);
    }

    @Override
    public IPage<Role> page(IPage<Role> page, Wrapper<Role> queryWrapper) {
        return this.baseMapper.selectPagePreLoad(page, queryWrapper);
    }

    private boolean insertPermissions(Role role) {
/*        List<String> menuCodeList = role.getMenuList().stream().map(menu -> menu.getMenuCode()).collect(Collectors.toList());
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_code", menuCodeList);
        List<Menu> menus = menuMapper.selectList(queryWrapper);*/
        List<Menu> menus = role.getMenuList();
        if (menus == null || menus.size() == 0) {
            return true;
        }
        List<Permission> permissions = menus.stream().map(
                menu -> {
                    Permission permission = new Permission();
                    permission.setMenuId(menu.getId());
                    permission.setRoleId(role.getId());
                    return permission;
                }
        ).collect(Collectors.toList());
        permissionMapper.insertBatchSomeColumn(permissions);
        return true;
    }

    @Override
    public boolean save(Role role) {
        if (role == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        this.baseMapper.insert(role);

        this.insertPermissions(role);
        return true;
    }

    @Override
    public boolean updateById(Role role) {
        if (role == null || StringUtils.isEmpty(role.getId())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }

        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", role.getId());
        permissionMapper.delete(wrapper);
        this.baseMapper.updateById(role);

        insertPermissions(role);
        return true;
    }


/*    @Override
    public boolean removeById(Serializable id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", id);
        permissionMapper.delete(wrapper);
        QueryWrapper<UserRole> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("role_id", id);
        userRoleMapper.delete(wrapper1);
        this.baseMapper.deleteById(id);
        return true;
    }*/
}
