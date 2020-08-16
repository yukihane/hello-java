package com.example.autotestingexample;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyServiceImpl implements MyService {

    private final Clock clock;

    @Override
    public String getToday() {
        return LocalDate.now(clock).format(DateTimeFormatter.ISO_DATE);
    }
}
