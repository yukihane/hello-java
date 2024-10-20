package com.example.gradle_kotlin_with_devtools

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MyController {

    @RequestMapping
    fun index(model: Model): String {
        model.addAttribute("name", "Gradle v9")
        return "index"
    }
}
