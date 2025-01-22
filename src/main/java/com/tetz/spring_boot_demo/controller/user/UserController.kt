package com.tetz.spring_boot_demo.controller.user

import com.tetz.spring_boot_demo.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController(@Autowired private val userService: UserService) {

    @GetMapping("/jdbc")
    fun findAllSingleton(model: Model): String {
        // ?: 연산자를 사용해서 데이터 베이스에서 null 리턴 시, 빈 리스트를 전달하여 런타임 에러 방지
        val users = userService.findAllJdbc() ?: emptyList()
        model.addAttribute("users", users)
        return "user"
    }

    @GetMapping("/mybatis")
    fun findAllMyBatis(model: Model): String {
        val users = userService.findAllMybatis() ?: emptyList()
        model.addAttribute("users", users)
        return "user"
    }

    @GetMapping("/jpa")
    fun findAllJpa(model: Model): String {
        val users = userService.findAllJpa() ?: emptyList()
        model.addAttribute("users", users)
        return "user"
    }
}