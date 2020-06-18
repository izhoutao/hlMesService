package com.haili.ucenter.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.haili.framework.domain.ucenter.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTest {
    @Autowired
    IRoleService roleService;
    @Autowired
    IMenuService menuService;


    @Test
    public void testAddMenu() {
        LambdaQueryWrapper<Menu> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Menu::getCode, "productionLine");
        List<Menu> list = menuService.list(lambdaQueryWrapper);

        List<Menu> list1 = new LinkedList<>();
        list.forEach(item -> {
            LocalDateTime now = LocalDateTime.now();
            list1.add(new Menu().setName(item.getName() + ":查询")
                    .setCode(StrUtil.toUnderlineCase(item.getCode()) + "_list")
                    .setPid(item.getId()).setType("2").setCreateTime(now).setUpdateTime(now));
            list1.add(new Menu().setName(item.getName() + ":添加")
                    .setCode(StrUtil.toUnderlineCase(item.getCode()) + "_save")
                    .setPid(item.getId()).setType("2").setCreateTime(now).setUpdateTime(now));
            list1.add(new Menu().setName(item.getName() + ":更新")
                    .setCode(StrUtil.toUnderlineCase(item.getCode()) + "_update")
                    .setPid(item.getId()).setType("2").setCreateTime(now).setUpdateTime(now));
            list1.add(new Menu().setName(item.getName() + ":删除")
                    .setCode(StrUtil.toUnderlineCase(item.getCode()) + "_delete")
                    .setPid(item.getId()).setType("2").setCreateTime(now).setUpdateTime(now));
        });
        menuService.saveBatch(list1);

    }


}