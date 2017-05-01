package com.congybk.webapp.controller;

import com.congybk.entity.Event;
import com.congybk.entity.EventMember;
import com.congybk.entity.History;
import com.congybk.entity.User;
import com.congybk.service.*;
import com.congybk.utlis.Constans;
import com.congybk.utlis.PushNotificationUtils;
import com.congybk.webapp.model.FormEvent;
import com.congybk.webapp.model.FormHistoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Author YNC on 30/03/2017.
 */
@Controller
@RequestMapping(value = "/event")
public class WebEventController {

    @Autowired
    EventService mEventService;
    @Autowired
    OrganizationService mOrganizationService;
    @Autowired
    TownService mTownService;
    @Autowired
    UserService mUserService;
    @Autowired
    HttpSession mSession;
    @Autowired
    EventMemberService mMemberService;
    @Autowired
    HistoryService historyService;

    @RequestMapping(value = "/create")
    public String createEvent(Model event, Model organization, Model town) {
        if (mSession.getAttribute(Constans.SESSION_EMAIL) != null) {
            event.addAttribute("formEvent", new Event());
            organization.addAttribute("organizations", mOrganizationService.getAll());
            town.addAttribute("towns", mTownService.getAll());
            return "create-event";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/handle-create-event")
    public String handleCreateEvent(@ModelAttribute FormEvent formEvent) {
        Event event = new Event();
        event.setEventName(formEvent.getName());
        String bloodType = "";
        bloodType = formEvent.getA() != null ? formEvent.getA() : bloodType;
        bloodType = formEvent.getAb() != null ? bloodType + "," + formEvent.getAb() : bloodType;
        bloodType = formEvent.getB() != null ? bloodType + "," + formEvent.getB() : bloodType;
        bloodType = formEvent.getO() != null ? bloodType + "," + formEvent.getO() : bloodType;
        event.setBloodType(bloodType);
        String time = formEvent.getTime().replace("T", " ");
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        Date date = null;
        try {
            date = dt.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event.setTime(date);
        event.setAddress(formEvent.getAddress());
        event.setTown(mTownService.findById(Integer.parseInt(formEvent.getTown())));
        event.setContent(formEvent.getContent());
        event.setOrganization(mOrganizationService.findById(Integer.parseInt(formEvent.getOrganization())));
        event.setUser(mUserService.findByEmail((String) mSession.getAttribute(Constans.SESSION_EMAIL)));
        event.setStatus(false);
        event.setCreateAt(new Date());
        if (mEventService.create(event) != null) {
            List<User> users = new ArrayList<>();
            String[] bloodTypes = bloodType.split(",");
            switch (formEvent.getOption()) {
                case "4": {
                    for (String bloodType1 : bloodTypes) {
                        List<User> userBlood = mUserService.findByTownIdAndBloodType(Integer.parseInt(formEvent.getTown()), bloodType1);
                        users.addAll(userBlood);
                    }
                    PushNotificationUtils.pushNotification(users, formEvent.getName(), formEvent.getContent());
                    break;
                }
                case "2": {
                    users = new ArrayList<>();
                    bloodTypes = bloodType.split(",");
                    for (String bloodType1 : bloodTypes) {
                        List<User> userBlood = mUserService.findByBloodType(bloodType1);
                        System.out.println(bloodType1 + "| |" + userBlood.size());
                        users.addAll(userBlood);
                    }
                    PushNotificationUtils.pushNotification(users, formEvent.getName(), formEvent.getContent());
                    break;
                }
                case "3": {
                    users = mUserService.findByTownId(Integer.parseInt(formEvent.getTown()));
                    PushNotificationUtils.pushNotification(users, formEvent.getName(), formEvent.getContent());
                    break;
                }
                default: {
                    users = mUserService.getAll();
                    PushNotificationUtils.pushNotification(users, formEvent.getName(), formEvent.getContent());
                }
            }
            return "redirect:/event/1";
        }

        return "create-event";
    }

    @RequestMapping(value = "/{id}")
    public String index(Model model, @PathVariable int id) {
        long number = mEventService.getCount();
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
        model.addAttribute("page", mListPage);
        model.addAttribute("current", id);
        model.addAttribute("maxPage", page);
        model.addAttribute("events", mEventService.getListEvent(start, end));
        return "event";
    }

    @RequestMapping(value = "/user/{id}")
    public String getListUser(@PathVariable String id, Model model) {
        model.addAttribute("event", mEventService.findById(Integer.parseInt(id)));
        model.addAttribute("users", mMemberService.getListMemberByEvent(mEventService.findById(Integer.parseInt(id))));
        return "view-member-event";
    }

    @RequestMapping(value = "/user/add")
    public String addHistory(@ModelAttribute FormHistoryEvent history) {
        History his = new History();
        User user = mUserService.findById(history.getId());
        his.setUser(user);
        his.setNote(history.getNote());
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        Date date = null;
        try {
            date = dt.parse(history.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (user.getBloodType() == null || user.getBloodType().equals("")) {
            user.setBloodType(history.getBloodType());
            mUserService.update(user);
        }
        List<EventMember> eventMembers = mMemberService.findByEventAndUser(history.getEventId(), history.getId());
        if (eventMembers.size() != 0) {
            eventMembers.get(0).setStatus(true);
            mMemberService.update(eventMembers.get(0));
        }
        his.setTime(date);
        return "redirect:/event/user/" + history.getEventId();
    }

    @RequestMapping(value = "/forward/{id}")
    public String forwardBlood(@PathVariable String id) {
        Event event = mEventService.findById(Integer.parseInt(id));
        event.setStatus(true);
        mEventService.update(event);
        return "redirect:/event/1";
    }


}
