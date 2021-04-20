package com.ocean.blog.service;

import com.ocean.blog.NotFoundException;
import com.ocean.blog.dao.TypeRepository;
import com.ocean.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    //新增
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }


    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
    @Transactional
    @Override
    //更新
    public Type updateType(Long id,Type type) {
        Type t = typeRepository.getOne(id);//首先查询需要更新的id，将信息保存在对象t中
        if (t==null){//判断表中是否有这个类型，不存在就抛出资源为找到异常
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);//将需要更新的type(source)复制到查到的对象t(target)
        return typeRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);

    }
}
