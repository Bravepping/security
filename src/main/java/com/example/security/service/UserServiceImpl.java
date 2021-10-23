package com.example.security.service;

import com.example.security.dao.UserDao;
import com.example.security.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public UserInfo getUser(String username) {
        return userDao.getUser(username);
    }
}
