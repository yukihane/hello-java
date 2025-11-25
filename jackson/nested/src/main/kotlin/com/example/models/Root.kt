package com.example.models

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = TypeA::class, name = "type-a"),
    JsonSubTypes.Type(value = TypeB::class, name = "type-b"),
    JsonSubTypes.Type(value = TypeC::class, name = "type-c")
)
sealed class Root {
    abstract val name: String
    abstract val type: String
}
