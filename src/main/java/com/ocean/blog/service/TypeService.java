package com.ocean.blog.service;

import com.ocean.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {
    //新增类型，返回一个Type类型
    Type saveType(Type type);
    //查询类型
    Type getType(Long id);
    //按分类名字查询
    Type getTypeByName(String name);
    //分页查询
    Page<Type> listType(Pageable pageable);
    //更新类型
    Type updateType(Long id,Type type);
    //删除类型
    void deleteType(Long id);
}
