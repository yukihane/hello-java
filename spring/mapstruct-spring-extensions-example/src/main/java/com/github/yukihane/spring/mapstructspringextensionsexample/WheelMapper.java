package com.github.yukihane.spring.mapstructspringextensionsexample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface WheelMapper extends Converter<Wheel, WheelDto> {
    @Override
    @Mapping(source = "size", target = "wheelSize")
    WheelDto convert(Wheel wheel);
}
