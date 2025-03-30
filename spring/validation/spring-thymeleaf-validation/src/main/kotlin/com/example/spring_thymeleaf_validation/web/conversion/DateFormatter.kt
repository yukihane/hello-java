package com.example.spring_thymeleaf_validation.web.conversion

import org.springframework.context.MessageSource
import org.springframework.format.Formatter
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter(
    private val messageSource: MessageSource,
) : Formatter<Date> {
    override fun parse(text: String, locale: Locale): Date {
        val dateFormat = createDateFormat(locale)
        return dateFormat.parse(text)
    }

    override fun print(date: Date, locale: Locale): String {
        val dateFormat = createDateFormat(locale)
        return dateFormat.format(date)
    }

    private fun createDateFormat(locale: Locale): SimpleDateFormat {
        val format = messageSource.getMessage("date.format", null, locale)
        val dateFormat = SimpleDateFormat(format)
        dateFormat.isLenient = false
        return dateFormat
    }
}
