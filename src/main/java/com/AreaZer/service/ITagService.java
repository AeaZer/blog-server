package com.AreaZer.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.AreaZer.entity.Tag;

import java.util.List;

public interface ITagService extends IService<Tag> {
      List<Tag> getIndexTags();

}
