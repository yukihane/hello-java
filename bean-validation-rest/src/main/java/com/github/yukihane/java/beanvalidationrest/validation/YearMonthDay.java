package com.github.yukihane.java.beanvalidationrest.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 相関項目チェックのサンプル
 */
@Constraint(validatedBy = YearMonthDayValidator.class)
@Target({ TYPE, METHOD, CONSTRUCTOR, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@Repeatable(YearMonthDay.List.class)
public @interface YearMonthDay {

    String message() default "{com.github.yukihane.java.beanvalidationrest.validation.YearMonthDay.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();

    @Target({ TYPE, METHOD, CONSTRUCTOR, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        YearMonthDay[] value();
    }
}