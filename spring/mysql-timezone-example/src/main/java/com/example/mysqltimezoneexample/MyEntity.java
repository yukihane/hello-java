package com.example.mysqltimezoneexample;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "my_entity")
@Getter
@Setter
public class MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "my_instant")
    private Instant myInstant;

    @Column(name = "my_datetime")
    private LocalDateTime myDateTime;

    @Column(name = "my_date")
    private LocalDate myDate;
}
