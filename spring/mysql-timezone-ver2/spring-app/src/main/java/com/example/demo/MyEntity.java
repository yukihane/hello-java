package com.example.demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "my_entities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private OffsetDateTime myUtcOffsetDateTime;
    private OffsetDateTime myJstOffsetDateTime;
    private OffsetDateTime myDefaultOffsetDateTime;
    private ZonedDateTime myUtcZonedDateTime;
    private ZonedDateTime myJstZonedDateTime;
    private ZonedDateTime myDefaultZonedDateTime;

    // timestamp
    private Instant myInstantStamp;
    private OffsetDateTime myUtcOffsetDateTimeStamp;
    private OffsetDateTime myJstOffsetDateTimeStamp;
    private OffsetDateTime myDefaultOffsetDateTimeStamp;
}
