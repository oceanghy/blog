package com.ocean.blog.service;

import com.ocean.blog.NotFoundException;
import com.ocean.blog.dao.BlogRepository;
import com.ocean.blog.po.Blog;
import com.ocean.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        //Inferred type 'S' for type parameter 'S' is not within its bound; should extend 'java.lang.Long'
        //看看是不是主键位置写错了
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b==null){
            throw new NotFoundException("博客不存在");
        }
        BeanUtils.copyProperties(blog,b);
        return blogRepository.save(b);
    }

    @Override
    //Specification使用这个类来实现SQL的动态拼接，实现分页查询条件添加
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                /*
                    Root<Blog>:将Blog对象封装成root
                    CriteriaQuery:查询条件的容器，存放查询条件并使用
                    CriteriaBuilder：查询条件的构建器
                 */
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    //按照title来查询
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if (blog.getType().getId() != null){
                    //判断type的id是否相等
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blog.getType().getId()));
                }
                if (blog.isRecommend()){
                    //判断推荐是否为true
                    predicates.add(criteriaBuilder.equal(root.get("recommend"),blog.isComment()));
                }

                //将查询条件拼接
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

                return null;
            }
        },pageable);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);

    }
}
