package org.hibernate.validator.referenceguide.chapter01;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car {

    @NotNull
    private final String manufacturer;

    @NotNull
    @Size(min = 2, max = 14)
    private final String licensePlate;

    @Min(2)
    private final int seatCount;

    public Car(final String manufacturer, final String licencePlate, final int seatCount) {
        this.manufacturer = manufacturer;
        this.licensePlate = licencePlate;
        this.seatCount = seatCount;
    }

    // getters and setters ...
}