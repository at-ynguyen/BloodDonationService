package com.congybk.controller;

import com.congybk.entity.Event;
import com.congybk.entity.EventMember;
import com.congybk.entity.History;
import com.congybk.entity.User;
import com.congybk.response.EventResponse;
import com.congybk.response.ObjectResponse;
import com.congybk.service.EventMemberService;
import com.congybk.service.EventService;
import com.congybk.service.HistoryService;
import com.congybk.service.UserService;
import com.congybk.utlis.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @Author YNC on 14/04/2017.
 */
@RestController
@RequestMapping(value = "/api/event")
public class ApiEventController {
    @Autowired
    EventService mEventService;
    @Autowired
    EventMemberService eventMemberService;
    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;

    @GetMapping(value = "/{page}")
    public ResponseEntity<?> getEventByPage(@PathVariable int page) {
        int start = page * 10 - 10;
        int end = 10;
        List<EventResponse> eventResponses = new ArrayList<>();
        List<Event> events = mEventService.getListEvent(start, end);
        for (int i = 0; i < events.size(); i++) {
            eventResponses.add(new EventResponse(eventMemberService.getListMemberByEvent(events.get(i)).size(), events.get(i)));
        }
        return new ResponseEntity<Object>(eventResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {
        Event event = mEventService.findById(id);
        return new ResponseEntity<Object>(new EventResponse(eventMemberService.getListMemberByEvent(event).size(), event), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/member/{id}")
    public ResponseEntity<?> joinEvent(@PathVariable int id) {
        User user = this.userService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        Event event = mEventService.findById(id);
        List<EventMember> eventMembers = eventMemberService.findByEventAndUser(event.getId(), user.getId());
        if (eventMembers.size() == 0) {
            eventMemberService.create(new EventMember(event, user, false, ""));
        } else {
            return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Object>(event, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/check/{id}")
    public ResponseEntity<?> checkEvent(@PathVariable int id) {
        User user = this.userService.findByEmail(UserUtils.getAccountCodeByAuthorization());
        Event event = mEventService.findById(id);
        List<EventMember> eventMembers = eventMemberService.findByEventAndUser(event.getId(), user.getId());
        if (eventMembers.size() == 0) {
            List<History> histories = historyService.getListHistoryByUser(user);
            Calendar calendar1 = getCalendar(event.getTime().toString());
            System.out.print("TIME:" + event.getTime().toString());
            if (histories.size() == 0) {
                return new ResponseEntity<Object>(new ObjectResponse(1, "Tham gia"), HttpStatus.OK);
            } else {
                System.out.print("TIME:" + histories.get(0).getTime().toString());
                Calendar calendar2 = getCalendar(histories.get(0).getTime().toString());
                if (getNumberMonthBetWeenTwoDays(calendar1, calendar2) >= 3) {
                    return new ResponseEntity<Object>(new ObjectResponse(1, "Tham gia"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Object>(new ObjectResponse(2, "Bạn không đủ điều kiện để tham gia đợt hiến máu này"), HttpStatus.OK);
                }
            }
        } else {
            return new ResponseEntity<Object>(new ObjectResponse(2, "Bạn đã đăng ký tham gia"), HttpStatus.OK);
        }
    }

    public int getNumberMonthBetWeenTwoDays(Calendar c1, Calendar c2) {
        int diffYear = Math.abs(c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR));
        return diffYear * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    }

    public Calendar getCalendar(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(date));
            return calendar;
        } catch (ParseException e) {
            throw new RuntimeException("Illegal date: " + date, e);
        }
    }

}
