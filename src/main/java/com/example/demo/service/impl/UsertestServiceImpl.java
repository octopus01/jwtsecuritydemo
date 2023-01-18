package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UsertestDao;
import com.example.demo.entity.Usertest;
import com.example.demo.service.UsertestService;
import org.springframework.stereotype.Service;

/**
 * (Usertest)表服务实现类
 *
 * @author makejava
 * @since 2023-01-17 15:11:37
 */
@Service("usertestService")
public class UsertestServiceImpl extends ServiceImpl<UsertestDao, Usertest> implements UsertestService {

}

