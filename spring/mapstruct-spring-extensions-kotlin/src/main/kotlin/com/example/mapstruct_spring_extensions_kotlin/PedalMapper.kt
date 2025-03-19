package com.example.mapstruct_spring_extensions_kotlin

import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface PedalMapper {
    @Mapping(source = "size", target = "pedalSize")
    fun convert(pedal: Pedal): PedalDto
}
