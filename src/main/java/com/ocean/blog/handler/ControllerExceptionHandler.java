package com.ocean.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
/*
    统一拦截的错误页面
 */
@ControllerAdvice//这个注解会拦截所有Controller拦截的东西
public class ControllerExceptionHandler {
    //声明日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)//标识这个方法可以做异常处理
    //springMVC中的ModelAndView返回一个页面，以及后台输出的信息，HttpServletRequest访问的哪一个路径出现异常，e传递异常信息
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {

        //记录异常的URL以及异常信息
        logger.error("Request URL: {} , Exception e:{}",request.getRequestURL(),e);

        //通过一个判断来判断是否为状态码，如果是，直接将这个异常抛出，让springboot来处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            //通过AnnotationUtils注解工具来中的findAnnotation方法来查看
            throw e;
        }

        //需要返回这个ModelAndView页面，先声明ModelAndView对象
        ModelAndView mv = new ModelAndView();
        //将发生错误的url和发生的异常信息，添加到这个mv
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        //将mv返回到自定义的error页面
        mv.setViewName("error/error");
        return mv;
    }
}
