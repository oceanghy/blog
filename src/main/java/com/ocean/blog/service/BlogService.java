package com.ocean.blog.service;

import com.ocean.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Blog updateBlog(Long id,Blog blog);

    Page<Blog> listBlog(Pageable pageable,Blog blog);//查询用到的Blog中的属性，所以需要传递Blog

    void deleteBlog(Long id);


}
