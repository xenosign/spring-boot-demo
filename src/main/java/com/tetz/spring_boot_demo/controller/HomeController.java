package com.tetz.spring_boot_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    @ResponseBody
    public String home() {
        return "Hello";
    }
}