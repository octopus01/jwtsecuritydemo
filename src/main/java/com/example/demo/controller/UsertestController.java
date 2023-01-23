package com.example.demo.controller;

import com.example.demo.entity.Usertest;
import com.example.demo.security.utils.JwtUtil;
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
    @PostMapping("login")
    public String login(@RequestBody(required = false) Usertest usertest) {
        usertest=new Usertest();
        usertest.setUsername("1");
        usertest.setPassword("2");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (usertest.getUsername(), usertest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(usertest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return token;
    }
    @GetMapping("list")
    public String list(){
        SecurityContextHolder.getContext().getAuthentication();
        return "list";
    }
}
