package com.hb.blog.controllers;

import com.hb.blog.dtos.UserFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublicController {
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/new")
    public ModelAndView form() {
        UserFormDTO userForm = new UserFormDTO("","");
        ModelAndView mav = new ModelAndView("userForm");
        mav.addObject("userForm", userForm);

        return mav;
    }

    @PostMapping("/add_user")
    public ModelAndView addUser(@ModelAttribute UserFormDTO userFormDTO) {

        return new ModelAndView("redirect:/");
    }
}
