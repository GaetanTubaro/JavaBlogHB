package com.hb.blog.controllers;

import com.hb.blog.dtos.UserFormDTO;
import com.hb.blog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class CrudUserController {

    private UserService userService;

    public CrudUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public ModelAndView formNewUser() {
        UserFormDTO userForm = new UserFormDTO("","");
        ModelAndView mav = new ModelAndView("userForm");
        mav.addObject("userForm", userForm);

        return mav;
    }

    @PostMapping("/new")
    public ModelAndView addUser(@ModelAttribute UserFormDTO userFormDTO) {

        userService.addUser(userFormDTO);
        return new ModelAndView("redirect:/");
    }
}
