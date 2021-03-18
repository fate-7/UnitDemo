package com.cheng.unit.coreapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cheng.unit.coreapi.EncrpytServer;
import com.cheng.unit.coreapi.dao.UserMapper;
import com.cheng.unit.coreapi.entity.User;
import com.cheng.unit.coreapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author chengzhiqi
 * @date 2021/3/17 6:41 下午
 **/

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private EncrpytServer encrpytServer;

    @Autowired(required = false)
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired(required = false)
    public void setEncrpytServer(EncrpytServer encrpytServer) {
        this.encrpytServer = encrpytServer;
    }

    @Override
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> listsUserByQuery(String username) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        return userMapper.selectList(query);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateById(user) > 0;
    }


    @Override
    public String getPassword(User user) {
        final User encrypt = this.encrypt(user);
        return encrypt.getPassword();
    }

    private User encrypt(User user) {
        String password = user.getPassword();
        final byte[] encode = Base64Utils.encode(password.getBytes(StandardCharsets.UTF_8));
        user.setPassword(new String(encode));
        return user;
    }

    public static String getUserSign() {
        return "XX";
    }

    public String getUserAttr() {
        return getUserSign();
    }

    public Boolean isLinkServer(User user) {
        return encrpytServer.isNormal(user);
    }
}
