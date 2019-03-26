package com.github.yukihane.java.beanvalidationrest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlwaysNgValidator implements ConstraintValidator<AlwaysNg, Object> {

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        return false;
    }

}
