package com.example.datetime_to_json

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MyController {

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("dto", MyDto.generate())
        return "index"
    }

    @GetMapping("/api")
    @ResponseBody
    fun api(): MyDto {
        return MyDto.generate()
    }
}
