package com.example.record_spring_mvc_form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Data
public class Person {

    @NotNull
    @NotEmpty
    private String name;

    @DateTimeFormat(iso = DATE)
    private LocalDate birthDate;

    private Short age;
}
