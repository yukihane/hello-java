package com.github.yukihane.java.beanvalidationrest.validation;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClassNameValidator implements ConstraintValidator<ClassName, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return Pattern.matches("\\d\\-\\d", value);
    }

}
