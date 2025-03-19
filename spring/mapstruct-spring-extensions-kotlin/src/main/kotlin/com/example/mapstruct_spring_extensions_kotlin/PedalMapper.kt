package com.example.mapstruct_spring_extensions_kotlin

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.core.convert.converter.Converter

@Mapper
interface PedalMapper : Converter<Pedal, PedalDto> {
    @Mapping(source = "size", target = "pedalSize")
    override fun convert(pedal: Pedal): PedalDto
}
