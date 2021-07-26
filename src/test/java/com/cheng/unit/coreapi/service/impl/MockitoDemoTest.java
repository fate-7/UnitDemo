package com.cheng.unit.coreapi.service.impl;

import com.cheng.unit.coreapi.CoreapiApplicationTests;
import com.cheng.unit.coreapi.dao.UserMapper;
import com.cheng.unit.coreapi.entity.User;
import io.qameta.allure.junit4.DisplayName;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

/**
 * Mockito 使用demo
 * @author chengzhiqi
 * @date 2021/3/18 7:35 下午
 **/
@Slf4j
public class MockitoDemoTest extends CoreapiApplicationTests {

    private UserServiceImpl userService;

    private UserMapper userMapper;

    /**
     * 使用mockito 模拟依赖 set到被测试类中, 所以需要使用set注入的方式
     */
    @Before
    public void setUp() {
        userService = new UserServiceImpl();
        userMapper = Mockito.mock(UserMapper.class);
        userService.setUserMapper(userMapper);
    }


    /**
     * 普通方法mock
     */
    @Test
    @DisplayName("test normal function")
    public void testNormal() {
        User xiaoHua = User.builder().username("XiaoHua").password("111111").build();
        User xiaoMing = User.builder().username("XiaoMing").password("111111").build();
        BDDMockito.given(userMapper.selectById(eq(1))).willReturn(xiaoHua);
        BDDMockito.given(userMapper.selectById(eq(2))).willReturn(xiaoMing);
        User resultOne = userService.findById(1);
        User resultTwo = userService.findById(2);

        Assertions.assertEquals(xiaoHua.getUsername(), resultOne.getUsername());
        Assertions.assertEquals(xiaoMing.getUsername(), resultTwo.getUsername());
        Mockito.verify(userMapper, times(2)).selectById(any());

    }


}
