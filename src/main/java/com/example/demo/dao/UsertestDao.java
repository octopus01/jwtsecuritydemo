package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Usertest;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Usertest)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-17 15:11:33
 */
@Mapper
public interface UsertestDao extends BaseMapper<Usertest> {
}

