package com.example.jackson_json_date_type_convert

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDate

@Controller
class MyController {

    @GetMapping
    fun get(myForm: MyForm): String {
        return "index"
    }

    @PostMapping
    fun post(myForm: MyForm): String {
        println(myForm.toString())
        return "index"
    }

    @GetMapping("/json")
    @ResponseBody
    fun json(): MyForm {
        return MyForm(
            date1 = LocalDate.of(2021, 1, 1),
            date2 = null,
        )
    }
}
