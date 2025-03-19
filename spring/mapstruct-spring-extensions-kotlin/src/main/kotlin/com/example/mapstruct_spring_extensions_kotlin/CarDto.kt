package com.example.mapstruct_spring_extensions_kotlin

data class CarDto(
    val name: String,
    val steeringWheel: WheelDto,
    val footPedal: PedalDto,
)
