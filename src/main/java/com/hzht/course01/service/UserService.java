package com.hzht.course01.service;

import com.hzht.course01.entity.User;

import java.util.List;

/**
 * @author 黄昭鸿
 * @create 2019-08-01 下午8:27
 */
public interface UserService {

    User getUserByName(String name);

    User getUser(Long id);

    User getUser(Long id, String name);

    List<User> getAll();

    void insertUser(User user);
}
