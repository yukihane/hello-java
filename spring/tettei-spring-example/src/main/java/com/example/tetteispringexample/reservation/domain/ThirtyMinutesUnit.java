package com.example.tetteispringexample.reservation.domain;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.*;
import javax.validation.*;

@Documented
@Constraint(validatedBy = { ThirtyMinutesUnitValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ThirtyMinutesUnit {
    String message() default "{mrs.app.reservation.ThirtyMinutesUnit.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        ThirtyMinutesUnit[] value();
    }
}
