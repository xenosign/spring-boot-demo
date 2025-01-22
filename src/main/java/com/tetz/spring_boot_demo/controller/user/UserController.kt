package com.tetz.spring_boot_demo.controller.user

import com.tetz.spring_boot_demo.service.user.UserService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping("/jdbc")
    fun findAllSingleton(model: Model): String {
        val users = userService.findAllJdbc()
        model.addAttribute("users", users)
        return "user"
    }

    @GetMapping("/mybatis")
    fun findAllMyBatis(model: Model): String {
        val users = userService.findAllMybatis()
        model.addAttribute("users", users)
        return "user"
    }

    @GetMapping("/jpa")
    fun findAllJpa(model: Model): String {
        val users = userService.findAllJpa()
        model.addAttribute("users", users)
        return "user"
    }
}