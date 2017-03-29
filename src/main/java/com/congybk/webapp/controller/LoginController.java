package com.congybk.webapp.controller;

import com.congybk.entity.User;
import com.congybk.service.UserService;
import com.congybk.webapp.model.FromLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author YNC on 28/03/2017.
 */
@Controller
public class LoginController {
    public static final String SESSION_KEY = "EMAIL";
    @Autowired
    HttpSession mSession;
    @Autowired
    private UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "login")
    public String login(Model model) {
        if (mSession.getAttribute(SESSION_KEY) == null) {
            model.addAttribute("formLogin", new FromLogin());
            return "login";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/handleLogin")
    public String handleLogin(@ModelAttribute FromLogin fromLogin) {
        System.out.print(fromLogin.getEmail());
        User user = userService.findByEmail(fromLogin.getEmail());
        if (user == null || !passwordEncoder.matches(fromLogin.getPassword(), user.getPassword())) {
            return "redirect:/login";
        }
        mSession.setAttribute(SESSION_KEY, fromLogin.getEmail());
        return "redirect:/home";
    }

    @RequestMapping(value = "home")
    public String home() {
        return "home";
    }

}
