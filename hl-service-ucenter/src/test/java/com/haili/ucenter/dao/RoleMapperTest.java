package com.haili.ucenter.dao;

import com.haili.framework.domain.ucenter.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleMapperTest {
    @Autowired
    RoleMapper roleMapper;

    @Test
    public void testroleMapper() {
        Role role = new Role();
        role.setId("67744bfdd78a41b38c29e66ecd85c2b4");
        role.setDescription("hahahaha");
        roleMapper.updateById(role);
    }
}