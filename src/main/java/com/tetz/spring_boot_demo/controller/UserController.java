package com.tetz.spring_boot_demo.controller;

import com.tetz.spring_boot_demo.entity.User;
import com.tetz.spring_boot_demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/mybatis")
    public String findAllMyBatis(Model model) {
        List<User> users = userServiceImpl.findAllMybatis();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/jpa")
    public String findAllJpa(Model model) {
        List<User> users = userServiceImpl.findAllJpa();
        model.addAttribute("users", users);
        return "user";
    }
}