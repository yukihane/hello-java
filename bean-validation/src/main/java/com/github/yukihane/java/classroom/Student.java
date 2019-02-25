package com.github.yukihane.java.classroom;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotNull
    private String name;
}
