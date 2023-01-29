package com.example.demo.controller;

import com.example.demo.dao.UsertestDao;
import com.example.demo.entity.Usertest;
import com.example.demo.security.entity.LoginUser;
import com.example.demo.security.utils.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.List;
import java.util.Map;

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

//    @PreAuthorize("hasRole('Admin')")
    @GetMapping("list")
    public String list(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return "list";
    }

    @Resource
    UsertestDao usertestDao;

    @GetMapping("all")
    public List<Usertest> getAll(){
        return usertestDao.getAllWithRoles();
    }
    @GetMapping("roles")
    public Usertest getRoles(){
        return usertestDao.getAllWithRolesById(1);
    }
    @GetMapping("roless/{id}")
    public List<String> getRole(@PathVariable Integer id){
        return usertestDao.getRolesById(id);
    }
}
