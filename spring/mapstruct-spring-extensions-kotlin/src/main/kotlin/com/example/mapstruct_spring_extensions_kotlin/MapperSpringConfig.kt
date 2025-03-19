package com.example.mapstruct_spring_extensions_kotlin

import org.mapstruct.MapperConfig
import org.mapstruct.MappingConstants
import org.mapstruct.extensions.spring.SpringMapperConfig

@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING)
@SpringMapperConfig
interface MapperSpringConfig
