package com.github.yukihane.springboot.hellographql.bean;

import java.util.List;
import lombok.Data;

@Data
public class ClassRoom {

    private String name;

    private List<Student> students;
}
