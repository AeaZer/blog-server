package com.AreaZer.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.AreaZer.entity.User;

import java.util.List;

public interface IUserService extends IService<User> {
    User getAdminInfo();
    User verifyLogin(String username, String password);
    List<String> getPermissionList(Long usId);
    boolean isAdmin(Long usId);
    PageInfo<User> getListByPage(Integer pageNum, Integer pageSize, Wrapper<User> queryWrapper);
}
