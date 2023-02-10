package com.hb.blog.controllers;

import com.hb.blog.dtos.LocalUserDTO;
import com.hb.blog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("private/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ModelAndView adminPage() {

        List<LocalUserDTO> users = userService.getUsers();
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("users", users);

        return mav;
    }
}
