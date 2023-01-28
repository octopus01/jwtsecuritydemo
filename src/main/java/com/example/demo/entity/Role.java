package com.example.demo.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2023-01-29 02:20:48
 */
@SuppressWarnings("serial")
public class Role extends Model<Role> {
    
    private Integer id;
    
    private String roleName;
    
    private String doctor;
    
    private Date createTime;
    
    private Date updateTime;
    
    private String key;
    
    private Integer datascope;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getDatascope() {
        return datascope;
    }

    public void setDatascope(Integer datascope) {
        this.datascope = datascope;
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

