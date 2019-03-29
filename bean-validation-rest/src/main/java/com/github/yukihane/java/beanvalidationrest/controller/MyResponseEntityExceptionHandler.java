package com.github.yukihane.java.beanvalidationrest.controller;

import com.github.yukihane.java.beanvalidationrest.controller.response.ValidationError;
import com.github.yukihane.java.beanvalidationrest.controller.response.ValidationResponse;
import com.github.yukihane.java.beanvalidationrest.validation.AdditionalData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationResponse> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException ex) {

        final String exStr = toString(ex);
        //        log.error(exStr);

        final List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            final HibernateConstraintViolation<?> cv = e.unwrap(HibernateConstraintViolation.class);
            final AdditionalData ad = cv.getDynamicPayload(AdditionalData.class);

            final List<String> paths;
            if (ad != null) {
                //                paths = ad.getFields().stream().map(f -> e.getCode() + "." + f)
                //                    .collect(Collectors.toList());
                paths = ad.getFields().stream().map(f -> e.getField() + "." + f).collect(Collectors.toList());

            } else {
                paths = Arrays.asList(e.getField());
            }
            final String code = e.getCode();

            final String message = e.getDefaultMessage();

            errors.add(new ValidationError(paths, code, message));
        });

        final ValidationResponse resp = new ValidationResponse();
        resp.setErrors(errors);
        log.error("{}", resp);

        final ResponseEntity<ValidationResponse> ret = new ResponseEntity<>(resp,
            HttpStatus.BAD_REQUEST);
        return ret;
    }

    private static String toString(final MethodArgumentNotValidException ex) {
        final StringBuilder b = new StringBuilder();

        final String message = ex.getMessage();
        b.append("message: " + message + "\n");
        b.append("[MethodParameter]\n");
        b.append(toString(ex.getParameter()));
        b.append("[BindingResult]\n");
        b.append(toString(ex.getBindingResult()));

        return b.toString();
    }

    private static Object toString(final BindingResult t) {
        final StringBuilder b = new StringBuilder();

        b.append("objectName: " + t.getObjectName() + "\n");
        b.append("nestedPath: " + t.getNestedPath() + "\n");
        b.append("errorCount: " + t.getErrorCount() + "\n");
        b.append("allErrors:\n" + toString(t.getAllErrors()) + "\n");
        b.append("globalErrorCount: " + t.getGlobalErrorCount() + "\n");
        b.append("globalErrors:\n" + toString(t.getGlobalErrors()) + "\n");
        b.append("fieldErrorCount: " + t.getFieldErrorCount() + "\n");
        b.append("fieldErrors:\n" + toString(t.getFieldErrors()) + "\n");

        b.append("target: " + t.getTarget() + "\n");
        b.append("model: " + t.getModel() + "\n");
        b.append("suppressedFields: " + t.getSuppressedFields());

        return b.toString();
    }

    private static String toString(final List<? extends ObjectError> t) {
        final StringBuilder b = new StringBuilder();
        t.forEach(e -> {
            b.append(e.toString() + "\n");
            b.append("arguments: " + Arrays.toString(e.getArguments()) + "\n");
            b.append("defaultMessage: " + e.getDefaultMessage() + "\n");
            final HibernateConstraintViolation<?> hcv = e.unwrap(HibernateConstraintViolation.class);
            final AdditionalData payload = hcv.getDynamicPayload(AdditionalData.class);
            if (payload != null) {
                b.append("fields: ** " + payload.getFields() + " **\n");
            }
        });

        return b.toString();
    }

    private static String toString(final MethodParameter t) {
        final StringBuilder b = new StringBuilder();
        b.append("executable: " + t.getExecutable().getName() + "\n");
        b.append("parameter: " + t.getParameterName() + "\n");

        return b.toString();
    }

}
