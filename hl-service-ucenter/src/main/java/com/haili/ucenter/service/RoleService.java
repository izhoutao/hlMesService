package com.haili.ucenter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.Permission;
import com.haili.framework.domain.ucenter.Role;
import com.haili.framework.domain.ucenter.UserRole;
import com.haili.framework.domain.ucenter.ext.RoleExt;
import com.haili.framework.domain.ucenter.response.RoleResult;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.framework.model.response.QueryResponseResult;
import com.haili.framework.model.response.QueryResult;
import com.haili.framework.model.response.ResponseResult;
import com.haili.ucenter.mapper.MenuMapper;
import com.haili.ucenter.mapper.PermissionMapper;
import com.haili.ucenter.mapper.RoleMapper;
import com.haili.ucenter.mapper.UserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    public QueryResponseResult findRoleList(long page, long size) {

        Page<Role> _page = new Page<>(page, size);
        IPage<Role> roleIPage = roleMapper.selectPage(_page, null);
        List<RoleExt> roleExts = roleIPage.getRecords().stream().map(role -> {
            QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
            permissionQueryWrapper.eq("role_id", role.getId());
            List<Permission> permissions = permissionMapper.selectList(permissionQueryWrapper);
            List<Menu> menuList = permissions.stream().map(permission -> {
                QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
                menuQueryWrapper.eq("id", permission.getMenuId());
                return menuMapper.selectOne(menuQueryWrapper);
            }).collect(Collectors.toList());
            RoleExt roleExt = new RoleExt();
            BeanUtils.copyProperties(role, roleExt);
            roleExt.setMenuList(menuList);
            return roleExt;
        }).collect(Collectors.toList());
        QueryResult<RoleExt> roleQueryResult = new QueryResult<>();
        roleQueryResult.setList(roleExts);
        roleQueryResult.setTotal(roleIPage.getTotal());
        //返回结果
        return new QueryResponseResult(CommonCode.SUCCESS, roleQueryResult);
    }


//    public RoleResult findRoleExtById(String roleId) {
//        if (roleId == null) {
//            ExceptionCast.cast(CommonCode.INVALID_PARAM);
//        }
//        RoleExt roleExt = roleMapper.findRoleExtById(roleId);
//
//        return new RoleResult(CommonCode.SUCCESS, roleExt);
//    }

    public RoleResult addRole(RoleExt roleExt) {
        if (roleExt == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
//        roleMapper.addRole(roleExt);
        LocalDateTime now = LocalDateTime.now();
        roleExt.setCreateTime(now);
        roleExt.setUpdateTime(now);
        roleMapper.insert(roleExt);
        Map<String, Object> saveParams = new HashMap<>();
        saveParams.put("roleId", roleExt.getId());
//        saveParams.put("menuIds", roleExt.getMenuList().stream().map(Menu::getId).collect(Collectors.toList()));
        saveParams.put("menuIds", roleExt.getMenuList().stream().map((menu) -> {
            QueryWrapper<Menu> menuWrapper = new QueryWrapper<>();
            menuWrapper.eq("menu_code", menu.getMenuCode());
            Menu menu1 = menuMapper.selectOne(menuWrapper);
            return menu1.getId();
        }).collect(Collectors.toList()));
        permissionMapper.savePermissions(saveParams);
        return new RoleResult(CommonCode.SUCCESS, roleExt);
    }

    public ResponseResult updateRole(String roleId, RoleExt roleExt) {
        if (StringUtils.isEmpty(roleId) || roleExt == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
//        deleteRole(roleId);
//        roleExt.setId(roleId);
//        addRole(roleExt);
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        permissionMapper.delete(wrapper);
        roleExt.setId(roleId);
        roleExt.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(roleExt);
        roleExt.getMenuList().stream().map((menu) -> {
            QueryWrapper<Menu> menuWrapper = new QueryWrapper<>();
            menuWrapper.eq("menu_code", menu.getMenuCode());
            Menu menu1 = menuMapper.selectOne(menuWrapper);
            return menu1;
        }).map((menu) -> {
            Permission permission = new Permission();
            permission.setMenuId(menu.getId());
            permission.setRoleId(roleId);
            permission.setCreateTime(LocalDateTime.now());
            return permission;
        }).forEach(permissionMapper::insert);

        return new ResponseResult(CommonCode.SUCCESS);
    }

    public ResponseResult deleteRole(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        permissionMapper.delete(wrapper);
        QueryWrapper<UserRole> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("role_id", roleId);
        userRoleMapper.delete(wrapper1);
        roleMapper.deleteById(roleId);
        return new ResponseResult(CommonCode.SUCCESS);
    }


}
