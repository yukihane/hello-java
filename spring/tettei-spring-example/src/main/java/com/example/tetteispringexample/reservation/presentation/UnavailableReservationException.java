package com.example.tetteispringexample.reservation.presentation;

public class UnavailableReservationException extends RuntimeException {
    public UnavailableReservationException(final String message) {
        super(message);
    }
}
