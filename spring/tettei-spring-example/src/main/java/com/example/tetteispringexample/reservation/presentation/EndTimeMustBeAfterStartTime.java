package com.example.tetteispringexample.reservation.presentation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.*;
import javax.validation.*;

@Documented
@Constraint(validatedBy = { EndTimeMustBeAfterStartTimeValidator.class })
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface EndTimeMustBeAfterStartTime {
    String message() default "{mrs.app.reservation.EndTimeMustBeAfterStartTime.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        EndTimeMustBeAfterStartTime[] value();
    }
}