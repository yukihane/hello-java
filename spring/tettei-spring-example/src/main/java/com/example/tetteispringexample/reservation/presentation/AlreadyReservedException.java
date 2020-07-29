package com.example.tetteispringexample.reservation.presentation;

public class AlreadyReservedException extends RuntimeException {
    public AlreadyReservedException(final String message) {
        super(message);
    }
}
