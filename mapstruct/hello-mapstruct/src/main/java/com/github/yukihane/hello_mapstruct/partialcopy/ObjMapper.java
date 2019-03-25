package com.github.yukihane.hello_mapstruct.partialcopy;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ObjMapper {
    ObjMapper INSTANCE = Mappers.getMapper(ObjMapper.class);

    void updateObj(PartObj part, @MappingTarget FullObj full);

}
