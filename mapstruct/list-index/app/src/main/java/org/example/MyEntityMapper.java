package org.example;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MyEntityMapper {
    MyEntityMapper INSTANCE = Mappers.getMapper(MyEntityMapper.class);

    MyEntity toEntity(MyEntityDto dto);
}
