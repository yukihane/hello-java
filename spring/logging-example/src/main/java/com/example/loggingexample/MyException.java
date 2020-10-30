package com.example.loggingexample;

public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MyException() {
        super();
    }

    public MyException(final String message) {
        super(message);
    }
}
