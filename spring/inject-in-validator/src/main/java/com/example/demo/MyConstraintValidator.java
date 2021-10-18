package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// カスタム validator
@Component
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, String> {

    private static final AtomicLong counter = new AtomicLong();
    private final ComplexBussinessLogic logic;
    private String prohibited;

    @Autowired
    public MyConstraintValidator(final ComplexBussinessLogic logic) {
        this.logic = logic;
        log.info("instanciate times: {}", counter.incrementAndGet());
    }

    @Override
    public void initialize(final MyConstraint constraintAnnotation) {
        this.prohibited = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return logic.isValidName(value, prohibited);
    }

}
