package com.ocean.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
    这是一个登陆拦截器
 */

public class LoginInterceptor implements HandlerInterceptor {//实现这个接口
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object o) throws Exception {
        if (request.getSession().getAttribute("user")==null){//判断session中是否有user
            response.sendRedirect("/admin");
            return false;//session中没有user返回false，不让执行
        }
        return true;
    }
}
