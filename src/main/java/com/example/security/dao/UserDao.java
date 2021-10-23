package com.example.security.dao;

import com.example.security.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao {
    UserInfo getUser(String username);
}
