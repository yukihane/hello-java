package com.example.kotlin_form_binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Data
public class JavaForm {
    /**
     * 名前(必須)
     */
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 10)
    private String name;

    /**
     * 生年月日(必須)
     */
    @NotNull
    @DateTimeFormat(iso = DATE)
    private LocalDate birthDate;

    /**
     * 年齢(任意)
     */
    private Short age;
}
