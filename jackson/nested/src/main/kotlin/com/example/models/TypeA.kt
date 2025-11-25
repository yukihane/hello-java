package com.example.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

sealed class TypeA : Root() {
    override val type: String = "type-a"
    abstract val skip: Boolean

    companion object {
        @JvmStatic
        @JsonCreator
        fun create(
            @JsonProperty("name") name: String,
            @JsonProperty("type") type: String? = null,
            @JsonProperty("skip") skip: Boolean,
            @JsonProperty("additional") additional: String? = null
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
