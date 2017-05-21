package com.congybk.webapp.controller;

import com.congybk.entity.Event;
import com.congybk.entity.EventMember;
import com.congybk.entity.Town;
import com.congybk.entity.User;
import com.congybk.response.EventResponse;
import com.congybk.service.*;
import com.congybk.webapp.model.FormAddUser;
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
 * @Author YNC on 09/05/2017.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    FindBloodService findBloodService;
    @Autowired
    HistoryService mHistoryService;
    @Autowired
    EventService mEventService;
    @Autowired
    EventMemberService eventMemberService;
    @Autowired
    UserService mUserService;

    @RequestMapping("/")
    public String index(Model model) {
        long number = findBloodService.getCount();
        long page = number / 5;
        if (page * 5 < number) {
            page++;
        }
        List<Long> mListPage = new ArrayList<>();
        for (long i = 1; i <= page; i++) {
            mListPage.add(i);
        }
        int start = 1 * 5 - 5;
        model.addAttribute("topHistory", mHistoryService.getTopHistory());
        model.addAttribute("findBlood", findBloodService.getFindBlood(start, 5));
        model.addAttribute("page", mListPage);
        model.addAttribute("current", 1);
        model.addAttribute("maxPage", page);
        return "index";
    }

    @RequestMapping("/{id}")
    public String index(@PathVariable int id,Model model) {
        long number = findBloodService.getCount();
        long page = number / 5;
        if (page * 5 < number) {
            page++;
        }
        List<Long> mListPage = new ArrayList<>();
        for (long i = 1; i <= page; i++) {
            mListPage.add(i);
        }
        int start = id * 5 - 5;
        model.addAttribute("topHistory", mHistoryService.getTopHistory());
        model.addAttribute("findBlood", findBloodService.getFindBlood(0, 5));
        model.addAttribute("page", mListPage);
        model.addAttribute("current", id);
        model.addAttribute("maxPage", page);
        return "index";
    }

    @RequestMapping("/index/event")
    public String indexEvent(Model model) {
        long number = mEventService.getCount();
        long page = number / 5;
        if (page * 5 < number) {
            page++;
        }
        List<Long> mListPage = new ArrayList<>();
        for (long i = 1; i <= page; i++) {
            mListPage.add(i);
        }
        int start = 1 * 5 - 5;
        List<EventResponse> eventResponses = new ArrayList<>();
        List<Event> events = mEventService.getListEvent(start, 5);
        for (int i = 0; i < events.size(); i++) {
            eventResponses.add(new EventResponse(eventMemberService.getListMemberByEvent(events.get(i)).size(), events.get(i)));
        }
        model.addAttribute("topHistory", mHistoryService.getTopHistory());
        model.addAttribute("event", eventResponses);
        model.addAttribute("page", mListPage);
        model.addAttribute("current", 1);
        model.addAttribute("maxPage", page);
        return "indexEvent";
    }

    @RequestMapping("/index/event/{id}")
    public String indexEventID(@PathVariable int id, Model model) {
        long number = mEventService.getCount();
        long page = number / 5;
        if (page * 5 < number) {
            page++;
        }
        List<Long> mListPage = new ArrayList<>();
        for (long i = 1; i <= page; i++) {
            mListPage.add(i);
        }
        int start = id * 5 - 5;
        List<EventResponse> eventResponses = new ArrayList<>();
        List<Event> events = mEventService.getListEvent(start, 5);
        for (int i = 0; i < events.size(); i++) {
            eventResponses.add(new EventResponse(eventMemberService.getListMemberByEvent(events.get(i)).size(), events.get(i)));
        }
        model.addAttribute("topHistory", mHistoryService.getTopHistory());
        model.addAttribute("event", eventResponses);
        model.addAttribute("page", mListPage);
        model.addAttribute("current", id);
        model.addAttribute("maxPage", page);
        return "indexEvent";
    }

    @RequestMapping("/index/view/event/{id}")
    public String viewEvent(@PathVariable int id, Model model) {
        Event event = mEventService.findById(id);
        model.addAttribute("topHistory", mHistoryService.getTopHistory());
        model.addAttribute("event", new EventResponse(eventMemberService.getListMemberByEvent(event).size(), event));
        return "view-event";
    }

    @RequestMapping(value = "/index/view/add")
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
            Event event = mEventService.findById(Integer.parseInt(user.getId()));
            List<EventMember> eventMember = eventMemberService.findByEventAndUser(event.getId(), userResult.getId());
            if (eventMember.size() == 0) {
                if (eventMemberService.create(new EventMember(event, userResult, false, "")) != null) {
                    return "redirect:/index/view/event/" + user.getId();
                }
            }

        }

        return "redirect:/index/view/event/" + user.getId();
    }

    @RequestMapping(value = "/index/view/findblood/{id}")
    public String getFindBloodById(@PathVariable int id, Model model) {
        model.addAttribute("findblood", findBloodService.findById(id));
        model.addAttribute("topHistory", mHistoryService.getTopHistory());
        return "view-find-blood";
    }
}
