package com.congybk.webapp.controller;

import com.congybk.entity.History;
import com.congybk.entity.User;
import com.congybk.service.HistoryService;
import com.congybk.service.UserService;
import com.congybk.webapp.model.FormHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping(value = "")
    public String history(Model model, Model history) {
        model.addAttribute("users", mUserService.getAll());
        history.addAttribute("history", new FormHistory());
        return "manager-history";
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
            return "redirect:/history";
        }
        return "home";
    }

    @RequestMapping(value = "/view/{id}")
    public String getListHistory(@PathVariable String id, Model model) {
        User user = mUserService.findById(Integer.parseInt(id));
        model.addAttribute("user", user);
        model.addAttribute("historys", mHistoryService.getListHistoryByUser(user));
        return "view-history";
    }


}
