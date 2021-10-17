package com.github.yukihane.spring.mapstructspringextensionsexample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = { WheelMapper.class, PedalMapper.class })
public interface CarMapper {
    @Mappings({
        @Mapping(source = "wheel", target = "steeringWheel"),
        @Mapping(source = "pedal", target = "footPedal"),
    })
    CarDto convert(Car car);
}
