package com.example.security.service;

import com.example.security.pojo.UserInfo;

public interface UserService {
    UserInfo getUser(String username);
}
