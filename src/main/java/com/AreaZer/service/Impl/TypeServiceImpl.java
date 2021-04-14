package com.AreaZer.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.AreaZer.entity.Type;
import com.AreaZer.mapper.TypeMapper;
import com.AreaZer.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getIndexTypes() {
        return typeMapper.getIndexTypes();
    }

}
