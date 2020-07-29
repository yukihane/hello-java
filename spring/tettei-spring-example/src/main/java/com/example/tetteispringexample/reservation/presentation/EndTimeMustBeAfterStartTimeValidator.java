package com.example.tetteispringexample.reservation.presentation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndTimeMustBeAfterStartTimeValidator
    implements ConstraintValidator<EndTimeMustBeAfterStartTime, ReservationForm> {
    private String message;

    @Override
    public void initialize(final EndTimeMustBeAfterStartTime constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final ReservationForm value, final ConstraintValidatorContext context) {
        if (value.getStartTime() == null || value.getEndTime() == null) {
            return true;
        }
        final boolean isEndTimeMustBeAfterStartTime = value.getEndTime().isAfter(value.getStartTime());
        if (!isEndTimeMustBeAfterStartTime) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode("endTime").addConstraintViolation();
        }
        return isEndTimeMustBeAfterStartTime;
    }
}