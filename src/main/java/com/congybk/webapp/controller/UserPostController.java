package com.congybk.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author YNC on 01/04/2017.
 */
@Controller
@RequestMapping(value = "/post")
public class UserPostController {
    @RequestMapping(value = "")
    public String index(){
        return "user-post";
    }
}
