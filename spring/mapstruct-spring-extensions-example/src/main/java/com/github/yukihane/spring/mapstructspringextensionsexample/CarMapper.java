package com.github.yukihane.spring.mapstructspringextensionsexample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = { WheelMapper.class, PedalMapper.class })
public interface CarMapper extends Converter<Car, CarDto> {
    @Override
    @Mappings({
        @Mapping(source = "wheel", target = "steeringWheel"),
        @Mapping(source = "pedal", target = "footPedal"),
    })
    CarDto convert(Car car);
}
