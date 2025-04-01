package org.example

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.factory.Mappers

@Mapper
interface MyEntityMapper {
    @Mapping(target = "children", source = "children", qualifiedByName = ["addIndex"])
    fun toEntity(dto: MyEntityDto): MyEntity

    @Mapping(target = "index", ignore = true)
    fun toEntity(dto: ChildEntityDto): ChildEntity

    @Named("addIndex")
    fun addIndex(dtos: List<ChildEntityDto>): Set<ChildEntity> {
        val entities: MutableSet<ChildEntity> = HashSet()
        for (i in dtos.indices) {
            val dto = dtos[i]
            val entity = toEntity(dto)
            entity.index = i // Add index to the child entity
            entities.add(entity)
        }
        return entities
    }

    companion object {
        val INSTANCE: MyEntityMapper = Mappers.getMapper(MyEntityMapper::class.java)
    }
}
