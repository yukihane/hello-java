package com.example.mapstruct_spring_extensions_kotlin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.ConversionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(
    private val conversionService: ConversionService,
) {
    private val log: Logger = LoggerFactory.getLogger(MyController::class.java)

    @PostMapping
    fun index(@RequestBody car: Car): CarDto {
        log.info("car: {}", car)

        val dto = conversionService.convert(car, CarDto::class.java)
            ?: throw IllegalArgumentException("Conversion failed")

        log.info("dto: {}", dto)

        return dto
    }
}
