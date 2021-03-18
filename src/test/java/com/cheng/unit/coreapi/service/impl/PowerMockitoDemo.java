package com.cheng.unit.coreapi.service.impl;


import com.cheng.unit.coreapi.CoreapiApplication;
import com.cheng.unit.coreapi.EncrpytServer;
import com.cheng.unit.coreapi.dao.UserMapper;
import com.cheng.unit.coreapi.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

/**
 * powermock 结合mockito使用方案
 */
//使用PowerMock的启动器
@SpringBootTest(classes = CoreapiApplication.class)
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PowerMockIgnore(value = { "javax.management.*", "javax.net.ssl.*", "javax.net.SocketFactory" })

/**
 * 重要：
 * 当需要mock final方法的时候，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是final方法所在的类。
 *
 * 当需要mock静态方法的时候，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是静态方法所在的类。
 *
 * 当需要mock私有方法的时候, 只是需要加注解@PrepareForTest，注解里写的类是私有方法所在的类
 */
@PrepareForTest(value = { UserServiceImpl.class , EncrpytServer.class})
@Slf4j
public class PowerMockitoDemo {

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
     * 使用spy的方式模拟私有方法:@spy使用的真实的对象实例，调用的都是真实的方法
     * @throws Exception
     */
    @Test
    public void testPrivateSpy() throws Exception {
        User xiaoHua = User.builder().username("XiaoHua").password("111111").build();
        UserServiceImpl spy = PowerMockito.spy(new UserServiceImpl());

        PowerMockito.doReturn(xiaoHua).when(spy, "encrypt",xiaoHua);

        String password = spy.getPassword(xiaoHua);

        Assertions.assertEquals(password, "111111");
        PowerMockito.verifyPrivate(spy, times(1)).invoke("encrypt", xiaoHua);
        //打印测试方法,测试case,简要描述等信息
        log.info("function name={}, case={}, description={}", "testNormal", "case", "description");
    }

    /**
     * 使用mock的方式模拟私有方法:@spy使用的真实的对象实例，调用的都是真实的方法
     * @throws Exception
     */
    @Test
    public void testPrivateMock() throws Exception {
        User xiaoHua = User.builder().username("XiaoHua").password("111111").build();
        UserServiceImpl mock = PowerMockito.mock(UserServiceImpl.class);

        PowerMockito.when(mock, "encrypt",xiaoHua).thenReturn(xiaoHua);

        //指明getPassword调用真实方法
        PowerMockito.when(mock.getPassword(xiaoHua)).thenCallRealMethod();
        String password = mock.getPassword(xiaoHua);

        Assertions.assertEquals(password, "111111");
        PowerMockito.verifyPrivate(mock, times(1)).invoke("encrypt", xiaoHua);

        //打印测试方法,测试case,简要描述等信息
        log.info("function name={}, case={}, description={}", "testNormal", "case", "description");
    }

    /**
     * 模拟静态方法
     */
    @Test
    public void testStatic() {
        PowerMockito.mockStatic(UserServiceImpl.class);
        PowerMockito.when(UserServiceImpl.getUserSign()).thenReturn("YY");

        String userAttr = userService.getUserAttr();
        Assertions.assertEquals(userAttr, "YY");

        //打印测试方法,测试case,简要描述等信息
        log.info("function name={}, case={}, description={}", "testNormal", "case", "description");
    }

    /**
     * 模拟final类
     */
    @Test
    public void testFinal() {
        User user = new User();
        EncrpytServer encrpytServer = PowerMockito.mock(EncrpytServer.class);
        PowerMockito.when(encrpytServer.isNormal(user)).thenReturn(false);

        userService.setEncrpytServer(encrpytServer);

        Boolean linkServer = userService.isLinkServer(user);

        Mockito.verify(encrpytServer, times(1)).isNormal(user);
        Assertions.assertEquals(linkServer, false);

        //打印测试方法,测试case,简要描述等信息
        log.info("function name={}, case={}, description={}", "testNormal", "case", "description");
    }

}