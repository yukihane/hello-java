package com.example.tetteispringexample.room.domain;

public class AlreadyReservedException extends RuntimeException {
    public AlreadyReservedException(final String message) {
        super(message);
    }
}
