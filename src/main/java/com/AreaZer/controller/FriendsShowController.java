package com.AreaZer.controller;



import com.AreaZer.aspect.MyLog;
import com.AreaZer.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendsShowController {


    @Autowired
    private ICacheService iCacheService;

    @MyLog
    @GetMapping("/friends")
    public String friends(Model model){
        model.addAttribute("friends",iCacheService.getIndexFriends());
        model.addAttribute("user",iCacheService.getAdminInfo());
        return "friends";

    }

}
