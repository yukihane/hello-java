package com.github.yukihane.java.beanvalidationrest.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MyResponseEntityExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        final MethodParameter param = ex.getParameter();
        LOG.info("parameterName: {}", param.getParameterName());
        LOG.info("parameterType: {}", param.getParameterType());

        final BindingResult br = ex.getBindingResult();
        LOG.info("Target: {}", br.getTarget());
        LOG.info("ErrorCount: {}", br.getErrorCount());

        LOG.info("AllErrors");
        br.getAllErrors().stream().forEach(e -> {
            LOG.info("objectName: {}", e.getObjectName());
            Stream.of(e.getCodes()).forEach(c -> {
                LOG.info("code: {}", c);
            });
            Stream.of(e.getArguments()).forEach(a -> {
                LOG.info("argument: {}", a);
            });
        });

        LOG.info("fieldErrors");
        br.getFieldErrors().stream().forEach(e -> {
            LOG.info("objectName: {}", e.getObjectName());
            LOG.info("field: {}", e.getField());
            Stream.of(e.getArguments()).forEach(a -> {
                LOG.info("argument: {}", a);
            });
        });

        final List<ConstraintViolationResponse> errors = br.getFieldErrors().stream()
            .map(e -> new ConstraintViolationResponse(e.getField(), e.getCodes(), e.getObjectName(),
                e.getDefaultMessage()))
            .collect(Collectors.toList());

        final ResponseEntity<Object> ret = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        return ret;
    }

}
