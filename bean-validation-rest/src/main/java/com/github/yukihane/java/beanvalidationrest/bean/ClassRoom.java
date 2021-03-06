package com.github.yukihane.java.beanvalidationrest.bean;

import com.github.yukihane.java.beanvalidationrest.validation.ClassName;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {

    @Min(1)
    private long id;

    @ClassName
    private String name;

    @NotNull
    @Valid
    private List<Student> students;
}
