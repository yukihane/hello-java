package com.example.mapstruct_spring_extensions_kotlin

import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface WheelMapper {
    @Mapping(source = "size", target = "wheelSize")
    fun convert(wheel: Wheel): WheelDto
}
