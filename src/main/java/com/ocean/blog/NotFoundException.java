package com.ocean.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    定义一个博客找不到时的异常,但是会被定义的ControllerExceptionHandler拦截，
    需要在ControllerExceptionHandler中判断
 */
@ResponseStatus(HttpStatus.NOT_FOUND)//需要加入这个ResponseStatus返回状态码，让spring知道这是个自定义的资源找不到异常，不然就只是继承了RuntimeException
//需要继承RuntimeException
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
    }
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
