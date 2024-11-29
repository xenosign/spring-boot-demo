package com.tetz.spring_boot_demo.controller.user;

import com.tetz.spring_boot_demo.entity.user.User;
import com.tetz.spring_boot_demo.entity.user.UserVo;
import com.tetz.spring_boot_demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/jdbc")
    public String findAllSingleton(Model model) {
        List<UserVo> users = userService.findAllJdbc();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/mybatis")
    public String findAllMyBatis(Model model) {
        List<User> users = userService.findAllMybatis();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/jpa")
    public String findAllJpa(Model model) {
        List<User> users = userService.findAllJpa();
        model.addAttribute("users", users);
        return "user";
    }

}