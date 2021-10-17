package com.github.yukihane.spring.mapstructspringextensionsexample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface PedalMapper extends Converter<Pedal, PedalDto> {
    @Override
    @Mapping(source = "size", target = "pedalSize")
    PedalDto convert(Pedal pedal);
}
