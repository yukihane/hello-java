package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @PersistenceContext
    private final EntityManager em;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        // アプリケーションのコンテキストを明示的に終了
        int exitCode = SpringApplication.exit(context, () -> 0);
        System.exit(exitCode);
    }

    @Override
    public void run(String... args) throws Exception {
        final var UTC = ZoneOffset.UTC;
        final var JST = ZoneOffset.ofHours(9);

        var entity = new MyEntity(
                // ID
                null,
                // non-instant date-time types
                LocalTime.of(8, 0),
                LocalDate.of(2025, 8, 8),
                LocalDateTime.of(2025, 8, 8, 8, 0),
                OffsetTime.of(8, 0, 0, 0, UTC),
                OffsetTime.of(8, 0, 0, 0, JST),
                // instant date-time types
                Calendar.getInstance(),
                new Date(),
                Instant.now(),
                OffsetDateTime.now(UTC),
                OffsetDateTime.now(JST),
                OffsetDateTime.now(),
                ZonedDateTime.now(UTC),
                ZonedDateTime.now(JST),
                ZonedDateTime.now(),
                // timestamp
                Instant.now(),
                OffsetDateTime.now(UTC),
                OffsetDateTime.now(JST),
                OffsetDateTime.now()
        );

        em.persist(entity);
    }

}
