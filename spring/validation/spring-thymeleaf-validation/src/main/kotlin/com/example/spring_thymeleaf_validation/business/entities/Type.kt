package com.example.spring_thymeleaf_validation.business.entities

enum class Type(private val value: String) {

    PLASTIC("PLASTIC"),
    WOOD("WOOD"),
    ;

    companion object {
        val ALL = arrayOf(PLASTIC, WOOD)

        fun forName(name: String): Type {
            return when (name.uppercase()) {
                "PLASTIC" -> PLASTIC
                "WOOD" -> WOOD
                else -> throw IllegalArgumentException("Name \"$name\" does not correspond to any Type")
            }
        }
    }

    override fun toString(): String {
        return value
    }
}
