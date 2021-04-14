package com.AreaZer.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.AreaZer.entity.Friend;
import com.AreaZer.mapper.FriendMapper;
import com.AreaZer.service.IFriendService;
import org.springframework.stereotype.Service;



@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {

}
