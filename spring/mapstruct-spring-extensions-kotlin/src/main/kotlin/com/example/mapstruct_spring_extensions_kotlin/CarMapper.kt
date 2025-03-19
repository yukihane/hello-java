package com.example.mapstruct_spring_extensions_kotlin

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings


@Mapper(uses = [WheelMapper::class, PedalMapper::class])
interface CarMapper {
    @Mappings(Mapping(source = "wheel", target = "steeringWheel"), Mapping(source = "pedal", target = "footPedal"))
    fun convert(car: Car): CarDto
}
