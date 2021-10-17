package com.github.yukihane.spring.mapstructspringextensionsexample;

import lombok.Data;

@Data
public class CarDto {
    private String name;
    private WheelDto steeringWheel;
    private PedalDto footPedal;
}
