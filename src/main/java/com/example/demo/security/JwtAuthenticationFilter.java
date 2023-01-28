package com.example.demo.security;

import com.example.demo.security.entity.LoginUser;
import com.example.demo.security.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
public class  JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    JwtUtil jwtUtil;
    @Resource
    UserDetailsService userDetailsService;
    @Resource
    RedisTemplate<String,Object> redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(jwtUtil.getHeader());
        log.info("header token:{}", token);
        //如果请求头中有token,则进行解析，并且设置认证信息
        if (token != null && token.trim().length() > 0) {
            //根据token获取用户名
            String username = jwtUtil.getSubjectFromToken(token);
            // 验证username,如果验证合法则保存到SecurityContextHolder
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // JWT验证通过，使用Spring Security 管理
                if (jwtUtil.validateToken(token, userDetails)) {
                    //加载用户、角色、权限信息
                    String uuid = (String) jwtUtil.getClaimsFromToken(token).get("uuid");
                    LoginUser loginUser=(LoginUser)redisTemplate.opsForValue().get("user"+uuid);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        //如果请求头中没有Authorization信息则直接放行
        filterChain.doFilter(request, response);
    }
}
