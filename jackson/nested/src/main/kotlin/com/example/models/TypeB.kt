package com.example.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

sealed class TypeB : Root() {
    override val type: String = "type-b"
    abstract val skip: Boolean

    companion object {
        @JvmStatic
        @JsonCreator
        fun create(
            @JsonProperty("name") name: String,
            @JsonProperty("type") type: String? = null,
            @JsonProperty("skip") skip: Boolean,
            @JsonProperty("additional") additional: String? = null
        ): TypeB {
            return if (skip) {
                TypeBChild1(name)
            } else {
                TypeBChild2(name, additional ?: throw IllegalArgumentException("additional is required when skip is false"))
            }
        }
    }
}

data class TypeBChild1(
    override val name: String
) : TypeB() {
    override val skip: Boolean = true
}

data class TypeBChild2(
    override val name: String,
    val additional: String
) : TypeB() {
    override val skip: Boolean = false
}
