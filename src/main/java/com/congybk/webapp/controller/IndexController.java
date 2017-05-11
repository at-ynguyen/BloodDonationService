package com.congybk.webapp.controller;

import com.congybk.entity.Event;
import com.congybk.response.EventResponse;
import com.congybk.service.EventMemberService;
import com.congybk.service.EventService;
import com.congybk.service.FindBloodService;
import com.congybk.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("topHistory", mHistoryService.getTopHistory());
        model.addAttribute("findBlood", findBloodService.getFindBlood(0, 5));
        return "index";
    }

    @RequestMapping("/index/event")
    public String indexEvent(Model model) {
        List<EventResponse> eventResponses = new ArrayList<>();
        List<Event> events = mEventService.getListEvent(0, 5);
        for (int i = 0; i < events.size(); i++) {
            eventResponses.add(new EventResponse(eventMemberService.getListMemberByEvent(events.get(i)).size(), events.get(i)));
        }
        model.addAttribute("topHistory", mHistoryService.getTopHistory());
        model.addAttribute("event", eventResponses);
        return "indexEvent";
    }

}
