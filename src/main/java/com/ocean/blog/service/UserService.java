package com.ocean.blog.service;

import com.ocean.blog.po.User;

/*
    检查用户名密码
 */
public interface UserService {
    User checkUser(String username,String password);
}
