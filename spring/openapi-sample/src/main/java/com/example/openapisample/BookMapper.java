package com.example.openapisample;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapi.example.model.BookModel;

@Mapper
public interface BookMapper {
    @Mapping(source = "title", target = "bookTitle")
    Book modelToEntity(BookModel model);

    @Mapping(source = "bookTitle", target = "title")
    BookModel entityToModel(Book entity);
}
