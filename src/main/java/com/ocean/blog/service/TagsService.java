package com.ocean.blog.service;

import com.ocean.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagsService {
    Tag saveTag(Tag tag);

    Tag getTag (Long id);

    Tag getTypeByName(String name);

    Page<Tag> listTag(Pageable pageable);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);
}
