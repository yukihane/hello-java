package com.example.datetime_to_json

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth

data class MyDto    (
    val datetime: LocalDateTime,
    val date: LocalDate,
    val time: LocalTime,
    val yearMonth: YearMonth,
) {
    companion object {
        fun generate() = MyDto(
            datetime = LocalDateTime.of(2024,11,10,10,10,10),
            date = LocalDate.of(2024,11,10),
            time = LocalTime.of(10,30,0),
            yearMonth = YearMonth.of(2024,11)
        )
    }
}
