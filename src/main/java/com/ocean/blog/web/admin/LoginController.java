package com.ocean.blog.web.admin;

import com.ocean.blog.Utils.MD5Util;
import com.ocean.blog.po.User;
import com.ocean.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/*
    这是一个登陆界面的拦截器
 */
@Controller
@RequestMapping("/admin")//全局的访问路径
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping//配置全局的访问路径后，用户访问admin就可以进入登陆界面
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")//post方式提交
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){//别忘了设置参数
        User user = userService.checkUser(username, MD5Util.code(password));
        if (user!=null){//判断checkUser方法返回的user是否为空，为空则为用户名或者密码错误
            //不要将密码放到session中
            user.setPassword(null);
            //不为空时，将user存放到session中
            session.setAttribute("user",user);
            return "admin/index";
        }else {//如果为空，使用重定向到原来的页面
            attributes.addFlashAttribute("message","用户名或密码错误");//重定向之后不要使用model来返回数据
            return "redirect:/admin/login";
        }
    }
    @GetMapping("/logout")//设置登出
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
