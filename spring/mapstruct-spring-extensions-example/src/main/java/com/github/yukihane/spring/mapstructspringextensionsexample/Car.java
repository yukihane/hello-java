package com.github.yukihane.spring.mapstructspringextensionsexample;

import lombok.Data;

@Data
public class Car {
    private String name;
    private Wheel wheel;
    private Pedal pedal;
}
