package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Usertest;

import java.util.List;
import java.util.Map;

/**
 * (Usertest)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-29 10:04:49
 */
public interface UsertestDao extends BaseMapper<Usertest> {
    List<Usertest> getAllWithRoles();
    Usertest getAllWithRolesById(Integer id);
    List<String> getRolesById(Integer id);
}

