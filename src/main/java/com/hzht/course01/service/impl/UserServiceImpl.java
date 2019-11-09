package com.hzht.course01.service.impl;

import com.hzht.course01.dao.UserMapper;
import com.hzht.course01.entity.User;
import com.hzht.course01.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 黄昭鸿
 * @create 2019-08-01 下午8:28
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }

    @Override
    public User getUser(Long id, String name) {
        return userMapper.getUserByIdAndName(id, name);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    /**
     * Transactional,事务配置
     * @param user
     */
    @Override
    @Transactional
    public void insertUser(User user){
        /*插入用户信息*/
        userMapper.insertUser(user);

        /*手动抛出异常*/
        throw new RuntimeException();
    }
}
