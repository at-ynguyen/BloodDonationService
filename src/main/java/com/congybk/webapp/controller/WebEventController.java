package com.congybk.webapp.controller;

import com.congybk.entity.Event;
import com.congybk.service.EventService;
import com.congybk.service.OrganizationService;
import com.congybk.service.TownService;
import com.congybk.service.UserService;
import com.congybk.utlis.Constans;
import com.congybk.webapp.model.FormEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        event.setStatus(true);
        if (mEventService.create(event) != null) {
            return "create-event";
        }

        return "redirect:/event";
    }

    @RequestMapping(value = "")
    public String index(Model event) {
        event.addAttribute("events", mEventService.getAll());
        return "event";
    }
}
