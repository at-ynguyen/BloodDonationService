package com.congybk.webapp.controller;

import com.congybk.entity.User;
import com.congybk.service.UserService;
import com.congybk.utlis.Constans;
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
    @Autowired
    HttpSession mSession;
    @Autowired
    private UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "login")
    public String login(Model model) {
        if (mSession.getAttribute(Constans.SESSION_EMAIL) == null) {
            model.addAttribute("formLogin", new FromLogin());
            return "login";
        }
        return "redirect:/event/create";
    }

    @RequestMapping(value = "/handleLogin")
    public String handleLogin(@ModelAttribute FromLogin fromLogin) {
        System.out.print(fromLogin.getEmail());
        User user = userService.findByEmail(fromLogin.getEmail());
        if (user == null || !passwordEncoder.matches(fromLogin.getPassword(), user.getPassword()) || user.getPermissionList().get(0).getRole().getId() != 1) {
            return "redirect:/login";
        }
        mSession.setAttribute(Constans.SESSION_EMAIL, fromLogin.getEmail());
        return "redirect:/event/create";
    }

    @RequestMapping(value = "home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "logout")
    public String logout() {
        mSession.removeAttribute(Constans.SESSION_EMAIL);
        return "redirect:/login";
    }

}
