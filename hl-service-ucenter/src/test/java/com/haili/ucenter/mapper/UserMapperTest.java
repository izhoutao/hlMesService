package com.haili.ucenter.mapper;

import com.haili.framework.domain.ucenter.ext.UserExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void findUserExtByUserName() {
        UserExt admin = userMapper.findUserExtByUserName("admin");
        System.out.println(admin);
    }
}