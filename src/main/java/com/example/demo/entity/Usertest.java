package com.example.demo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * (Usertest)表实体类
 *
 * @author makejava
 * @since 2023-01-17 15:11:36
 */
@SuppressWarnings("serial")
public class Usertest extends Model<Usertest>  {

    private Integer id;

    private  String username;

    private String password;

    private String roles;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public  String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

