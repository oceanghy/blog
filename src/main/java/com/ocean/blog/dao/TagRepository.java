package com.ocean.blog.dao;

import com.ocean.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
        Tag findByName (String name);
}
