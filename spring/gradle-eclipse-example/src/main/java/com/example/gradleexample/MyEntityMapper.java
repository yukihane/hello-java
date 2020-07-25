package com.example.gradleexample;

import org.mapstruct.Mapper;

@Mapper
public interface MyEntityMapper {
    MyEntity from(MyForm form);
}
