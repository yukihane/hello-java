package com.example.mysqltimezoneexample;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "my_entity")
@Data
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
