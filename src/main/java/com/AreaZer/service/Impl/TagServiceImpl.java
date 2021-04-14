package com.AreaZer.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.AreaZer.entity.Tag;
import com.AreaZer.mapper.TagMapper;
import com.AreaZer.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getIndexTags() {
        return tagMapper.getIndexTag();
    }

}
