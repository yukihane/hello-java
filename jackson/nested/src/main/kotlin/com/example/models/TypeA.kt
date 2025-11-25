package com.example.models

import com.fasterxml.jackson.annotation.JsonCreator

sealed class TypeA : Root() {
    override val type: String = "type-a"
    abstract val skip: Boolean

    companion object {
        @JvmStatic
        @JsonCreator
        fun create(
            name: String,
            type: String? = null,
            skip: Boolean,
            additional: String? = null
        ): TypeA {
            return if (skip) {
                TypeAChild1(name)
            } else {
                TypeAChild2(name, additional ?: throw IllegalArgumentException("additional is required when skip is false"))
            }
        }
    }
}

data class TypeAChild1(
    override val name: String
) : TypeA() {
    override val skip: Boolean = true
}

data class TypeAChild2(
    override val name: String,
    val additional: String
) : TypeA() {
    override val skip: Boolean = false
}
