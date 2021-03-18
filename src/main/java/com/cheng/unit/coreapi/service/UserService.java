package com.cheng.unit.coreapi.service;

import com.cheng.unit.coreapi.entity.User;

import java.util.List;

/**
 * @author chengzhiqi
 * @date 2021/3/17 6:40 下午
 **/

public interface UserService {

    User insert(User user);

    User findById(Integer id);

    List<User> listsUserByQuery(String username);

    Boolean deleteUser(Integer id);

    Boolean updateUser(User user);

    String getPassword(User user);
}
