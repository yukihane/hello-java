package com.example.tetteispringexample.room.domain;

public class UnavailableReservationException extends RuntimeException {
    public UnavailableReservationException(final String message) {
        super(message);
    }
}
