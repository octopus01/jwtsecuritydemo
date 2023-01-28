package com.example.demo.controller;

import com.example.demo.entity.Usertest;
import com.example.demo.security.entity.LoginUser;
import com.example.demo.security.utils.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UsertestController {
    @Resource
    UserDetailsService userDetailsServiceImpl;
    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    JwtUtil jwtUtil;
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @PostMapping("login")
    public String login(@RequestBody(required = false) Usertest usertest,HttpServletRequest request) {
        usertest=new Usertest();
        usertest.setUsername("1");
        usertest.setPassword("2");

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(usertest.getUsername());
        LoginUser loginUser = (LoginUser) userDetails;
        jwtUtil.setUserAgent(loginUser,request);
        String token = jwtUtil.generateToken(loginUser);
        redisTemplate.opsForValue().set("user"+loginUser.getUuid(),loginUser);
        loginUser.setToken(token);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (loginUser, usertest.getPassword());
        authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return token;
    }
    @GetMapping("list")
    public String list(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return "list";
    }
}
