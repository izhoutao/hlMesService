package com.haili.ucenter.service;

import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.ext.RoleExt;
import com.haili.framework.model.response.QueryResponseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @Test
    public void addRole() {
        RoleExt roleExt = new RoleExt();
        roleExt.setRoleCode("qwert code");
        roleExt.setRoleName("qwert name");
        roleExt.setDescription("qwert description");
        roleExt.setStatus("1");
        Menu m1 = new Menu();
        m1.setId("1111111111");
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(m1);
        Menu m2 = new Menu();
        m2.setId("2222222222");
        menus.add(m2);
        roleExt.setMenuList(menus);
        roleService.addRole(roleExt);
    }

    @Test
    public void updateRole() {
        RoleExt roleExt = new RoleExt();
        roleExt.setId("67744bfdd78a41b38c29e66ecd85c2b4");
        roleExt.setRoleCode("qwert code111");
        roleExt.setRoleName("qwert name111");
        roleExt.setDescription("qwert description111");
        roleExt.setStatus("1");
        Menu m1 = new Menu();
        m1.setId("3333333333");
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(m1);
        Menu m2 = new Menu();
        m2.setId("5555555555");
        menus.add(m2);
        roleExt.setMenuList(menus);
        roleService.updateRole(roleExt.getId(),roleExt);

    }

    @Test
    public void findRoleListPage() {
        QueryResponseResult roleListPage = roleService.findRoleList(0, 1);
        System.out.println(roleListPage);
    }
}