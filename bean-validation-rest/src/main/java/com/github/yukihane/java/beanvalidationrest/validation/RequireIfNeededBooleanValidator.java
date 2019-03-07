package com.github.yukihane.java.beanvalidationrest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class RequireIfNeededBooleanValidator implements ConstraintValidator<RequireIfNeeded, Object> {

    private String[] fields;

    @Override
    public void initialize(final RequireIfNeeded constraintAnnotation) {

        fields = constraintAnnotation.value();
        if (fields.length != 2) {
            throw new IllegalArgumentException("RequireIfNeeded needs just 2 parameters.");
        }
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        final BeanWrapper wrapper = new BeanWrapperImpl(value);
        final Object needed = wrapper.getPropertyValue(fields[0]);
        if (needed == null) {
            return true;
        } else if (needed instanceof Boolean) {
            if (!((Boolean) needed).booleanValue()) {
                return true;
            }
        } else if (needed instanceof CharSequence) {
            if (((CharSequence) needed).toString().isBlank()) {
                return true;
            }
        }

        final Object target = wrapper.getPropertyValue(fields[1]);
        if (target == null) {
            return false;
        }
        if (target instanceof CharSequence) {
            return !((CharSequence) target).toString().isEmpty();
        }

        return true;
    }

}
