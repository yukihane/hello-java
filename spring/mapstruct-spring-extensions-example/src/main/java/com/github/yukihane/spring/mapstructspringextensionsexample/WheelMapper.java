package com.github.yukihane.spring.mapstructspringextensionsexample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WheelMapper {
    @Mapping(source = "size", target = "wheelSize")
    WheelDto convert(Wheel wheel);
}
