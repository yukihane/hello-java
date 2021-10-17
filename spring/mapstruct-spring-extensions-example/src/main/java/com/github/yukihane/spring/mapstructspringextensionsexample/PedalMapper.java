package com.github.yukihane.spring.mapstructspringextensionsexample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PedalMapper {
    @Mapping(source = "size", target = "pedalSize")
    PedalDto convert(Pedal pedal);
}
