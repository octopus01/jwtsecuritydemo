package com.example.demo.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dao.UsertestDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserRole;
import com.example.demo.entity.Usertest;
import com.example.demo.security.entity.LoginUser;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UsertestService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UsertestService usertestService;
    @Resource
    UserRoleService userRoleService;
    @Resource
    RoleService roleService;
    @Resource
    UsertestDao usertestDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Usertest> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Usertest::getUsername,username);
        Usertest usertest = usertestService.getOne(lqw);

        if(usertest==null){
            log.info("不存在");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else {
            LambdaQueryWrapper<UserRole> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(UserRole::getUser, usertest.getId());
            List<UserRole> userRoles = userRoleService.list(lqw1);
            List<Integer> rolesId = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
            List<Role> roleList = roleService.listByIds(rolesId);
            List<String> roles = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());

            //连表查询mybatis版
            //List<String> roles = usertestDao.getRolesById(usertest.getId());
            return new LoginUser(username,
                        new BCryptPasswordEncoder().encode(usertest.getPassword()), roles);
        }
    }
}
