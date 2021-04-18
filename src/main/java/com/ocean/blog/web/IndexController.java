package com.ocean.blog.web;

import com.ocean.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
    这是一个拦截错误的拦截器
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
}
