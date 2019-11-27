package com.haili.ucenter.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haili.framework.domain.ucenter.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void getUserByUserName() {
        User admin = userMapper.getUserByUserName("admin");
        System.out.println(admin);
    }
    @Test
    public void selectPage() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.eq(User::getPhone,"13122172968");
        IPage<User> page = userMapper.selectPage(new Page<User>(1, 10), lambdaQueryWrapper);
        System.out.println(page.getRecords());
    }
    @Test
    public void selectList() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        lambdaQueryWrapper.eq(User::getPhone,"13122172968");
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }
    @Test
    public void selectList1() {
        User user = new User();
        user.setPhone("13122172968");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users.size());
    }
    @Test
    public void selectList2() {
        User user = new User();
        user.setPhone("13122172968");
        List<User> users = userMapper.selectList(new QueryWrapper<User>(user));
        System.out.println(users.size());
    }

}