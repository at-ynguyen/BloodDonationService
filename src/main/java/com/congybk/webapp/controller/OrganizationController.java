package com.congybk.webapp.controller;

import com.congybk.entity.Organization;
import com.congybk.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author YNC on 01/04/2017.
 */
@RequestMapping(value = "/organization")
@Controller
public class OrganizationController {
    @Autowired
    OrganizationService mService;

    @RequestMapping(value = "")
    public String organization(Model organization, Model organizations) {
        organization.addAttribute("organization", new Organization());
        organizations.addAttribute("organizations", mService.getAll());
        return "manager-organization";
    }

    @RequestMapping(value = "/add")
    public String organization(@ModelAttribute Organization organization) {
        if (mService.create(organization) != null) {
            return "redirect:/organization";
        }
        return "redirect:/organization";
    }

    @RequestMapping(value = "/edit")
    public String updateOrganization(@ModelAttribute Organization organization) {
        if (mService.update(organization) != null) {
            return "redirect:/organization";
        }
        return "redirect:/organization";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteOrganization(@PathVariable String id) {
        mService.delete(Integer.parseInt(id));
        return "redirect:/organization";
    }
}
