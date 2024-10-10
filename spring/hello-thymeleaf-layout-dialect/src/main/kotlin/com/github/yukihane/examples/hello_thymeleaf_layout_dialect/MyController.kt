package com.github.yukihane.examples.hello_thymeleaf_layout_dialect

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MyController {

    @GetMapping("/content1")
    fun content1(): String {
        return "content1"
    }
}