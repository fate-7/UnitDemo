package com.cheng.unit.coreapi.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cheng.unit.coreapi.CoreapiApplicationTests;
import com.cheng.unit.coreapi.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 整合H2的mapper层测试
 * @author chengzhiqi
 * @date 2021/3/17 4:49 下午
 **/
public class UserMapperTest extends CoreapiApplicationTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Before
    public void insert() {
        User user = User.builder().username("XiaoMing").password("123456").build();
        userMapper.insert(user);
    }

    @Test
    public void find() {
        LambdaQueryWrapper<User> query =new LambdaQueryWrapper<>();
        query.eq(User::getUsername, "XiaoMing");

        List<User> users = userMapper.selectList(query);

        Assertions.assertEquals("XiaoMing", users.get(0).getUsername());
    }
    
}
