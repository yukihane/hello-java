package com.github.yukihane.java.beanvalidationrest.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ConstraintViolationExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final BindingResult br = ex.getBindingResult();
        LOG.info("Target: {}", br.getTarget());
        LOG.info("AllErrors: {}", br.getAllErrors());
        LOG.info("ErrorCount: {}", br.getErrorCount());
        LOG.info("FieldErrors: {}", br.getFieldErrors());

        final List<Error> errors = br.getFieldErrors().stream()
                .map(e -> new Error(e.getField(), e.getCodes(), e.getObjectName()))
                .collect(Collectors.toList());

        final ResponseEntity<Object> ret = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        return ret;
    }

}
