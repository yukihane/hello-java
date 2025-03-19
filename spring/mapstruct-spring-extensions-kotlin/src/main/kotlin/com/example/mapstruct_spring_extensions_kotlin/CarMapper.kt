package com.example.mapstruct_spring_extensions_kotlin

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.springframework.core.convert.converter.Converter


@Mapper(uses = [WheelMapper::class, PedalMapper::class])
interface CarMapper : Converter<Car, CarDto> {
    @Mappings(Mapping(source = "wheel", target = "steeringWheel"), Mapping(source = "pedal", target = "footPedal"))
    override fun convert(car: Car): CarDto
}
