package com.example.mapstruct_spring_extensions_kotlin

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.springframework.core.convert.converter.Converter

@Mapper
interface WheelMapper : Converter<Wheel, WheelDto> {
    @Mapping(source = "size", target = "wheelSize")
    override fun convert(wheel: Wheel): WheelDto
}
