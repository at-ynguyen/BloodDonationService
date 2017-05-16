package com.congybk.webapp.controller;

import com.congybk.entity.FindBlood;
import com.congybk.entity.User;
import com.congybk.service.FindBloodService;
import com.congybk.service.UserService;
import com.congybk.utlis.PushNotificationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author YNC on 01/04/2017.
 */
@Controller
@RequestMapping(value = "/post")
public class UserPostController {
    @Autowired
    FindBloodService findBloodService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/{id}")
    public String index(Model model, @PathVariable int id) {
        long number = findBloodService.getCount();
        long page = number / 10;
        if (page * 10 < number) {
            page++;
        }
        List<Long> mListPage = new ArrayList<>();
        for (long i = 1; i <= page; i++) {
            mListPage.add(i);
        }
        int start = id * 10 - 10;
        int end = 10;
        model.addAttribute("findblood", findBloodService.getFindBlood(start, end));
        model.addAttribute("page", mListPage);
        model.addAttribute("current", id);
        model.addAttribute("maxPage", page);
        return "user-post";
    }

    @RequestMapping(value = "/check/{id}")
    public String censorshipPost(@PathVariable int id) {
        FindBlood findBlood = findBloodService.findById(id);
        findBlood.setApproved(true);
        findBloodService.update(findBlood);
        List<User> users = userService.findByBloodType(findBlood.getBloodType());
        PushNotificationUtils.pushNotification(users, findBlood.getPostName(), findBlood.getPostContent(),id,1);
        return "redirect:/post/1";
    }

    @RequestMapping(value = "/index/{page}")
    public String indexPage(Model model, @PathVariable int page) {
        int start = page * 10 - 10;
        int end = 10;
        model.addAttribute("posts", findBloodService.getFindBlood(start, end));
        return "index-post";
    }

}
