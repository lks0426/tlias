package com.wrx.filter;

import com.wrx.utils.CurrentHolder;
import com.wrx.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Filter拦截器，拦截所有请求，验证用户是否登录
 *  未登录，返回401
 *  JET验证未通过，返回401
 */
@Slf4j
@WebFilter(urlPatterns = "/*") // 拦截所有请求
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1、获取请求路径
        String requestURI = request.getRequestURI();

        // 2、判断用户是否是登录请求，如果路径包含/login，说明是登录请求，放行
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }

        // 3、获取请求头中的token
        String token = request.getHeader("token");

        // 4、判断token是否存在，如果不存在，说明用户没有登录，返回错误信息（响应状态码401）
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5、如果token存在，校验JWT令牌，如果校验失败，返回错误信息（响应状态码401）
        try {
            Claims claims = JwtUtils.parseToken(token);
            // 获取当前登录员工id
            Integer empId = Integer.valueOf(claims.get("id").toString());
            // 将登录人员id存入ThreadLocal中
            CurrentHolder.setCurrentId(empId);
            log.info("当前操作人员id：{}，将其存入该操作线程中的ThreadLocal中", empId);

        }catch (Exception e){
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        // 6、校验通过，放行
        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);

        // 删除ThreadLocal中存入的操作人员id，释放资源
        CurrentHolder.remove();
    }

}
