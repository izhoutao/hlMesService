package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haili.framework.domain.ucenter.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuMapperTest {
    @Autowired
    MenuMapper menuMapper;

    @Test
    public void selectList() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();

        List<Menu> menus = menuMapper.selectList(queryWrapper);
        System.out.println(menus);
    }
}