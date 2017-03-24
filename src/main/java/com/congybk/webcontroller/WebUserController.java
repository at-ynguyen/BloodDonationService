package com.congybk.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author YNC on 23/03/2017.
 */
@Controller
@RequestMapping("/web/user")
public class WebUserController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
