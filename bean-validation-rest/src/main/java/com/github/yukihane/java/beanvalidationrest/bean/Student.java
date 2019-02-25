package com.github.yukihane.java.beanvalidationrest.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Min(1)
    private long id;

    @Size(min = 1, max = 10)
    private String name;
}
