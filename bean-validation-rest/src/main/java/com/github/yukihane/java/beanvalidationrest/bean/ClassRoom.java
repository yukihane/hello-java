package com.github.yukihane.java.beanvalidationrest.bean;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {

    private long id;

    private String name;

    private List<Student> students;
}
