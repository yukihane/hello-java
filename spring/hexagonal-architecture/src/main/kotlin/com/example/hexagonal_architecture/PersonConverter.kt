package com.example.hexagonal_architecture

import org.mapstruct.Mapper

@Mapper
interface PersonConverter {
    fun toEntity(personForm: PersonForm): PersonEntity
}
