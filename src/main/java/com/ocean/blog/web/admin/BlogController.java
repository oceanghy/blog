package com.ocean.blog.web.admin;

import com.ocean.blog.po.Blog;
import com.ocean.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/blogs")
    public String blog(@PageableDefault(size = 7,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Blog blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs";
    }
    @GetMapping("/blogs/search")
    public String search(@PageableDefault(size = 7,sort = {"updateTime"},direction = Sort.Direction.DESC)
                               Pageable pageable, Blog blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }
}
