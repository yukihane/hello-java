package com.github.yukihane.java.beanvalidationrest.controller;

import com.github.yukihane.java.beanvalidationrest.controller.response.ConstraintViolationResponse;
import com.github.yukihane.java.beanvalidationrest.controller.response.ValidationResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationResponse> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException ex) {

        final MethodParameter param = ex.getParameter();
        log.info("parameterName: {}", param.getParameterName());
        log.info("parameterType: {}", param.getParameterType());

        final BindingResult br = ex.getBindingResult();
        log.info("Target: {}", br.getTarget());
        log.info("ErrorCount: {}", br.getErrorCount());

        log.info("AllErrors");
        br.getAllErrors().stream().forEach(e -> {
            log.info("objectName: {}", e.getObjectName());
            Stream.of(e.getCodes()).forEach(c -> {
                log.info("code: {}", c);
            });
            Stream.of(e.getArguments()).forEach(a -> {
                log.info("argument: {}", a);
            });
        });

        log.info("fieldErrors");
        br.getFieldErrors().stream().forEach(e -> {
            log.info("objectName: {}", e.getObjectName());
            log.info("field: {}", e.getField());
            Stream.of(e.getArguments()).forEach(a -> {
                log.info("argument: {}", a);
            });
        });

        final List<ConstraintViolationResponse> errors = br.getFieldErrors().stream()
            .map(e -> new ConstraintViolationResponse(e.getField(), e.getCodes(), e.getObjectName(),
                e.getDefaultMessage()))
            .collect(Collectors.toList());

        final ValidationResponse resp = new ValidationResponse();
        resp.setErrors(errors);

        final ResponseEntity<ValidationResponse> ret = new ResponseEntity<>(resp,
            HttpStatus.BAD_REQUEST);
        return ret;
    }

}
