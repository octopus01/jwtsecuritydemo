package com.example.demo.security.handler;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson2.JSONObject;
import com.example.demo.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws  IOException {
        log.info(e.getMessage());
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String r;
        if (e instanceof InsufficientAuthenticationException) r = JSONObject.toJSONString(Result.unAuthorized("权限不足"));
        else r = JSONObject.toJSONString(Result.unAuthorized(e.getMessage()));
        PrintWriter writer= response.getWriter();
        writer.write(r);
        writer.flush();
        writer.close();
    }
}
