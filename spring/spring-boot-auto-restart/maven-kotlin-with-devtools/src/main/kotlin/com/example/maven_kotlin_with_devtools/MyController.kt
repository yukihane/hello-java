package com.example.maven_kotlin_with_devtools

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MyController {

    @RequestMapping
    fun index(): String {
        return "index"
    }
}