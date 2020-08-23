package com.example.lombokmapstructexample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookMapper {

    @Mapping(source = "title", target = "bookTitle")
    Book modelToEntity(BookModel model);

    @Mapping(source = "bookTitle", target = "title")
    BookModel entityToModel(Book entity);
}
