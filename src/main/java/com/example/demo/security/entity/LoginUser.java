package com.example.demo.security.entity;

import com.example.demo.entity.Usertest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class LoginUser implements UserDetails {
    private String username;
    private String password;

    private String token;
//    /**
//     * 登录地址
//     */
//    private String loginLocation;
    @Getter
    private final String uuid = UUID.randomUUID().toString();
    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    private Set<GrantedAuthority> roles;


    public LoginUser()
    {
    }

    public LoginUser(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles=new HashSet<>();
        for(String role: roles){
            this.roles.add(new SimpleGrantedAuthority(role));
        }
    }
    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getToken(){
        return this.token;
    }

    public void setToken(String token) {
        this.token=token;
    }
    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }


}
