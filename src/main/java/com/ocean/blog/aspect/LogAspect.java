package com.ocean.blog.aspect;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/*
    这是一个记录日志的类，使用aop进行
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut( "execution(* com.ocean.blog.web.*.*(..))")
    public void log(){}

    //切面之前
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){//通过切面来获取调用的方法和请求参数
        //通过RequestContextHolder来获取Servlet中的ServletRequestAttributes
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //通过ServletRequestAttributes来获取HttpServletRequest，从而获取ip和url
        HttpServletRequest request = attributes.getRequest();
        StringBuffer requestURL = request.getRequestURL();
        String ip = request.getRemoteAddr();
        //通过joinPoint来获取方法类名和方法名
        String ClassMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+ joinPoint.getSignature().getName();
        //获取参数
        Object[] args = joinPoint.getArgs();
        //实例化内部类，封装所有参数
        RequestLog requestLog = new RequestLog(requestURL,ip,ClassMethod,args);
        logger.info("RequestLog: {}",requestLog);
    }
    @After("log()")
    public void doAfter(){
//       logger.info("------------------After---------------------------");
    }
    //相应之后返回的页面
    @AfterReturning(returning = "resultObject",pointcut = "log()")
    public void AfterReturn(Object resultObject){
        logger.info("Result:{}",resultObject);
    }
    //获取request中的相关参数
    @AllArgsConstructor
    @ToString
    private class RequestLog{
        private StringBuffer url;
        private String ip;
        private String ClassMethod;
        private Object[] args;

    }

}
