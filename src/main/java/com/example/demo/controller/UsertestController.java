package com.example.demo.controller;

import com.example.demo.entity.Usertest;
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

@RestController
public class UsertestController {
    @Resource
    UserDetailsService userDetailsServiceImpl;
    @Resource
    AuthenticationManager authenticationManager;
    @GetMapping("login")
    public String login(@RequestBody(required = false) Usertest usertest){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usertest=new Usertest();
        usertest.setUsername("1");
        usertest.setPassword("2");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (usertest.getUsername(), usertest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(usertest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "fuckyou";
    }
    @GetMapping("list")
    public String list(){
        SecurityContextHolder.getContext().getAuthentication();
        return "list";
    }
}
