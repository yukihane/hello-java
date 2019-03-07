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
 * 1項目目が {@code true}, {@code Boolean.TRUE}, 文字列型の場合にはブランクでない,
 * {@code null}でない場合に 2項目目が {@code null}でなく空文字列でも無いことを検証します。
 */
@Constraint(validatedBy = { RequireIfNeededBooleanValidator.class })
@Target({ TYPE, METHOD, CONSTRUCTOR, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@Repeatable(RequireIfNeeded.List.class)
public @interface RequireIfNeeded {

    String message() default "{com.github.yukihane.java.beanvalidationrest.validation.RequireIfNeeded.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();

    @Target({ TYPE, METHOD, CONSTRUCTOR, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        RequireIfNeeded[] value();
    }
}
