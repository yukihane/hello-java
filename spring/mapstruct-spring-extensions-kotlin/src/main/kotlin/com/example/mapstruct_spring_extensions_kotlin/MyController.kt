package com.example.mapstruct_spring_extensions_kotlin

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(
    private val carMapper: CarMapper,
) {
    private val log: Logger = LoggerFactory.getLogger(MyController::class.java)

    @PostMapping
    fun index(@RequestBody car: Car): CarDto {
        log.info("car: {}", car)

        val dto = carMapper.convert(car)

        log.info("dto: {}", dto)

        return dto
    }

}
