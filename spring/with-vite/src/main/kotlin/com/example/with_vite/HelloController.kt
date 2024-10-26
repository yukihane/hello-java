package com.example.with_vite

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/hello")
class HelloController {
    @GetMapping
    fun index(model: Model): String{
        model.addAttribute("name", "Vite")
        return "hello/index"
    }
}
