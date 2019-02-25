package com.github.yukihane.java.classroom;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassRoom {

    @Size(min = 3, max = 3)
    @NotNull
    private String name;

    @Valid
    private Collection<Student> students;

}
