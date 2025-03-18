package com.example.mysqltimezoneexample;

import java.time.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MyController {
    private static final ZoneOffset JST = ZoneOffset.ofHours(9);

    private final MyEntityRepository repository;

    @GetMapping
    public OffsetDateTime index() {
        final Instant now = Instant.now();

        final MyEntity e = new MyEntity();
        e.setMyInstant(now);
        e.setMyDateTime(LocalDateTime.ofInstant(now, JST));
        e.setMyDate(LocalDate.ofInstant(now, JST));
        e.setMyTime(LocalTime.of(10,10));

        repository.save(e);

        return OffsetDateTime.ofInstant(now, JST);
    }

}
