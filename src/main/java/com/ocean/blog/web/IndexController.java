package com.ocean.blog.web;

import com.ocean.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
    这是一个拦截器
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
//    @GetMapping("/admin")
//    public String blog(){
//        return "admin/index";
//    }
}
