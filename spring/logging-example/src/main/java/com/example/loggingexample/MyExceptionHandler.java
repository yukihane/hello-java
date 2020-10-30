package com.example.loggingexample;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-error-handling
@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyException.class)
    ResponseEntity<?> handleMyException(final HttpServletRequest request, final Throwable ex) {
        final MyExceptionResponse resp = new MyExceptionResponse();
        resp.setMessage(ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleException(final HttpServletRequest request, final Throwable ex) {
        return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
