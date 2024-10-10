package com.github.yukihane.examples.hello_thymeleaf_layout_dialect

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MyController {

    @GetMapping("/decorate/content1")
    fun content1(): String {
        return "decorate/content1"
    }

    @GetMapping("/decorate/content2")
    fun content2(): String {
        return "decorate/content2"
    }
}
