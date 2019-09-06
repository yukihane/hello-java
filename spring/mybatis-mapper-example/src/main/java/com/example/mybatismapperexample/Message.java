package com.example.mybatismapperexample;

import lombok.Data;
import lombok.NonNull;

@Data
public class Message {

    @NonNull
    private final String message;

    @Override
    public String toString() {
        return message;
    }

    public static Message of(final String message) {
        return new Message(message);
    }
}
