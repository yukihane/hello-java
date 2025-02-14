package com.example.each_for_one

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MyController {

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("message", MyData("Hello, World!"))
        model.addAttribute("messages", listOf(MyData("Hello, World!"), MyData("Goodbye, World!")))
        return "index"
    }
}

data class MyData(
    val text: String,
)
