package com.ocean.blog.service;

import com.ocean.blog.dao.UserRepository;
import com.ocean.blog.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
    处理用户登陆
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username,String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        return user;
    }
}
