package com.github.yukihane.hello_mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    private String make;
    private int numberOfSeats;
    private CarType type;
}
