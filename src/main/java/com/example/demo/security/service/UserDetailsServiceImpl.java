package com.example.demo.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.Usertest;
import com.example.demo.service.UsertestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UsertestService usertestService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Usertest> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Usertest::getUsername,username);
        Usertest usertest = usertestService.getOne(lqw);
        if(usertest==null){
            log.info("不存在");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else return new User(username,new BCryptPasswordEncoder().encode(usertest.getPassword()), new HashSet<>());
    }
}
