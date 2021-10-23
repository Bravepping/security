package com.example.security.controller;

import com.example.security.pojo.UserInfo;
import com.example.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    //转到登录页面
    @RequestMapping("/toLogin")
    public String login(){
        return "login";
    }

    @RequestMapping("/vip2")
    public String vip2(){
        return "vip2/index";
    }

    @RequestMapping("/vip1")
    public String vip1(){
        return "vip1/index";
    }

    @RequestMapping("/vip0")
    public String demo(){
        return "vip0/index";
    }

    @RequestMapping("/errorLogin")
    public String error(){
        return "loginError";
    }
}
