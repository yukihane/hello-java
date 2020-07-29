package com.example.tetteispringexample.reservation.domain;

import java.time.LocalTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ThirtyMinutesUnitValidator implements ConstraintValidator<ThirtyMinutesUnit, LocalTime> {
    @Override
    public void initialize(final ThirtyMinutesUnit constraintAnnotation) {
    }

    @Override
    public boolean isValid(final LocalTime value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.getMinute() % 30 == 0;
    }
}
