package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "my_entities")
@Getter
@Setter
public class MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // non-instant date-time types
    private LocalTime myLocalTime;
    private LocalDate myLocalDate;
    private LocalDateTime myLocalDateTime;
    private OffsetTime myUtcOffsetTime;
    private OffsetTime myJstOffsetTime;

    // instant date-time types
    private Calendar myCalendar;
    private Date myDate;
    private Instant myInstant;
    private OffsetDateTime myOffsetDateTime;
    private ZonedDateTime myUtcZonedDateTime;
    private ZonedDateTime myJstZonedDateTime;
    private ZonedDateTime myDefaultZonedDateTime;
}
