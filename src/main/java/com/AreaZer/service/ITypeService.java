package com.AreaZer.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.AreaZer.entity.Type;

import java.util.List;

public interface ITypeService extends IService<Type> {
    List<Type> getIndexTypes();

}
