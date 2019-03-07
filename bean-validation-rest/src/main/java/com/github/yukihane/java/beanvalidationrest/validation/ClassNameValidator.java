package com.github.yukihane.java.beanvalidationrest.validation;

import com.github.yukihane.java.beanvalidationrest.service.CheckService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@link ConstraintValidator} 実装の中でも {@link Autowired} が使えることを確認するためのサンプル。
 */
public class ClassNameValidator implements ConstraintValidator<ClassName, String> {

    @Autowired
    private CheckService service;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return service.checkRoomname(value);
    }

}
