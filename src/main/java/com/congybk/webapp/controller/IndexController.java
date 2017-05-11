package com.congybk.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author YNC on 09/05/2017.
 */
@Controller
@RequestMapping("/")
public class Index {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
