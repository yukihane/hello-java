package com.example.clock_bean

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Controller
class MyController(
    private val clock: Clock,
) {

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("defaultZone", ZoneId.systemDefault().toString())
        model.addAttribute("clockZone", clock.zone.toString())

        // システムのタイムゾーンが適用される
        model.addAttribute("localDateTime", LocalDateTime.now())
        // Clock で指定したタイムゾーンが適用される
        model.addAttribute("localDateTimeWithClock", LocalDateTime.now(clock))

        return "index"
    }
}
