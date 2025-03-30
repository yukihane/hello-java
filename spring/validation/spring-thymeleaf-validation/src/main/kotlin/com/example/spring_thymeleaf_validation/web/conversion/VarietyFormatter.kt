package com.example.spring_thymeleaf_validation.web.conversion

import com.example.spring_thymeleaf_validation.business.entities.Variety
import com.example.spring_thymeleaf_validation.business.services.VarietyService
import org.springframework.format.Formatter
import java.util.*

class VarietyFormatter(
    private val varietyService: VarietyService,
) : Formatter<Variety> {

    override fun parse(text: String, locale: Locale): Variety {
        val varietyId = Integer.valueOf(text)
        return varietyService.findById(varietyId)
            ?: throw IllegalArgumentException("Variety not found for id: $varietyId")
    }

    override fun print(variety: Variety, locale: Locale): String {
        return variety.id.toString() ?: ""
    }
}
