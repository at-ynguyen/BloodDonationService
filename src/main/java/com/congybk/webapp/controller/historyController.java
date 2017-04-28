package com.congybk.webapp.controller;

import com.congybk.entity.*;
import com.congybk.response.TopHistory;
import com.congybk.service.EventMemberService;
import com.congybk.service.EventService;
import com.congybk.service.HistoryService;
import com.congybk.service.UserService;
import com.congybk.webapp.model.FormAddUser;
import com.congybk.webapp.model.FormHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Author YNC on 01/04/2017.
 */
@Controller
@RequestMapping(value = "/history")
public class historyController {
    @Autowired
    UserService mUserService;

    @Autowired
    HistoryService mHistoryService;
    @Autowired
    EventMemberService eventMemberService;
    @Autowired
    EventService eventService;

    @RequestMapping(value = "/{id}")
    public String history(Model model, @PathVariable int id) {
        long number = mHistoryService.getCount();
        long page = number / 10;
        if (page * 10 < number) {
            page++;
        }
        List<Long> mListPage = new ArrayList<>();
        for (long i = 1; i <= page; i++) {
            mListPage.add(i);
        }
        int start = id * 10 - 10;
        List<User> users = mUserService.getListUser(start);
        List<TopHistory> topHistorys = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            topHistorys.add(new TopHistory(mHistoryService.getNumberDonationByUser(users.get(i)), users.get(i)));
        }
        model.addAttribute("topHistorys", topHistorys);
        model.addAttribute("history", new FormHistory());
        model.addAttribute("page", mListPage);
        model.addAttribute("current", id);
        model.addAttribute("maxPage", page);
        return "manager-history";
    }

    @RequestMapping(value = "/user/add")
    public String addUser(@ModelAttribute FormAddUser user) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = dt.parse(user.getBirthDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User userSearch = mUserService.findByCardId(user.getCardId());
        User userResult;
        if (userSearch != null) {
            userResult = userSearch;
        } else {
            User userAdd = new User(user.getCardId(), user.getFullName(), new Town(1, ""), date, user.getBloodType());
            userResult = mUserService.create(userAdd);
        }
        if (userResult != null) {
            Event event = eventService.findById(Integer.parseInt(user.getId()));
            List<EventMember> eventMember = eventMemberService.findByEventAndUser(event.getId(), userResult.getId());
            if (eventMember.size() == 0) {
                if (eventMemberService.create(new EventMember(event, userResult, false, "")) != null) {
                    return "redirect:/event/user/" + user.getId();
                }
            }

        }

        return "redirect:/event/user/" + user.getId();
    }

    @RequestMapping(value = "/add")
    public String addHistory(@ModelAttribute FormHistory history) {
        History his = new History();
        his.setUser(mUserService.findById(history.getId()));
        his.setNote(history.getNote());
        String time = history.getTime().replace("T", " ");
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        Date date = null;
        try {
            date = dt.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        his.setTime(date);
        if (mHistoryService.create(his) != null) {
            return "redirect:/history/1";
        }
        return "home";
    }

    @RequestMapping(value = "/view/{id}")
    public String getListHistory(@PathVariable String id, Model model) {
        User user = mUserService.findById(Integer.parseInt(id));
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("historys", mHistoryService.getListHistoryByUser(user));
            return "view-history";
        } else {
            return "error";
        }
    }


}
