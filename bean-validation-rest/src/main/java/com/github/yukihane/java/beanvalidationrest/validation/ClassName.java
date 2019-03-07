package com.github.yukihane.java.beanvalidationrest.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * バリデーション実装 {@link ClassNameValidator} にサービスをインジェクションしたような 制約のサンプル。
 */
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = ClassNameValidator.class)
@Documented
public @interface ClassName {
    String message() default "{com.github.yukihane.java.beanvalidationrest.validation.ClassName.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
