package com.example.mysqltimezoneexample;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MyController {

    private final MyEntityRepository repository;

    @GetMapping
    public OffsetDateTime index() {
        final Instant now = Instant.now();

        final MyEntity e = new MyEntity();
        e.setMyInstant(now);

        repository.save(e);

        return OffsetDateTime.ofInstant(now, ZoneOffset.ofHours(9));
    }

}
