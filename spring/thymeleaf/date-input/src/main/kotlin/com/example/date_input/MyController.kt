package com.example.date_input

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import java.time.LocalDate

@Controller
class MyController {

    @ModelAttribute("dto")
    fun dto(): Dto {
        return Dto()
    }

    @GetMapping
    fun index(model: Model): String {
        return "index"
    }
}

data class Dto(
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val date: LocalDate? = LocalDate.now(),
)
