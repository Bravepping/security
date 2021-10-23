package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }
    @Override
    //对身份进行验证
    protected void configure(HttpSecurity http) throws Exception {
        //permitAll()允许所有人访问
        http.authorizeRequests().antMatchers("/","index").permitAll()
                .antMatchers("/vip2/**").hasRole("2")
                .antMatchers("/vip1/**").hasRole("1");

        http.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/toLogin").loginProcessingUrl("/login").failureUrl("/errorLogin");
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //从内存验证
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("vip1").password(new BCryptPasswordEncoder().encode("123")).roles("1");
        //customUserDetailsService从数据库验证
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
