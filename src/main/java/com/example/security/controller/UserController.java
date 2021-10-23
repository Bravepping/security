package com.example.security.controller;


import com.example.security.pojo.UserInfo;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //验证是否连接数据库
    @GetMapping("/getUser")
    public UserInfo getUser(String username){
        return userService.getUser(username);
    }
}
