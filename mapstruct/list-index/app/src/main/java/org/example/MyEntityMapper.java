package org.example;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface MyEntityMapper {
    MyEntityMapper INSTANCE = Mappers.getMapper(MyEntityMapper.class);

    @Mapping(target = "children", source = "children", qualifiedByName = "addIndex")
    MyEntity toEntity(MyEntityDto dto);

    @Mapping(target = "index", ignore = true)
    ChildEntity toEntity(ChildEntityDto dto);

    @Named("addIndex")
    default Set<ChildEntity> addIndex(List<ChildEntityDto> dtos) {
        Set<ChildEntity> entities = new HashSet<>();
        for (int i = 0; i < dtos.size(); i++) {
            ChildEntityDto dto = dtos.get(i);
            var entity = toEntity(dto);
            entity.setIndex(i); // Add index to the child entity
            entities.add(entity);
        }
        return entities;
    }

}
