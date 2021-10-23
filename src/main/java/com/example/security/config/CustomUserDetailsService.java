package com.example.security.config;

import com.example.security.pojo.UserInfo;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * 新建类CustomUserDetailsService实现接口CustomUserDetailsService
 * 并重写loadUserByUsername方法
**/
@Component
public class CustomUserDetailsService implements UserDetailsService {

    //从service层拿数据 也可以直接从dao层取数据
    @Autowired
    private UserService userService;

    //需要加密 明文不能通过
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userinfo=userService.getUser(username);
        if (userinfo==null){
            throw new UsernameNotFoundException("用户不存在");
        }

        Integer role = userinfo.getRole();
        //固定写法
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        //返回加密后数据
        return new User(
                userinfo.getUsername(),
                // 因为数据库是明文，所以这里需加密密码
                passwordEncoder.encode(userinfo.getPassword()),
                authorities
        );
    }
}
