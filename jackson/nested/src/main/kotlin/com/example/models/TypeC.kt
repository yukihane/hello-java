package com.example.models

data class TypeC(
    override val name: String,
    val additional: String
) : Root() {
    override val type: String = "type-c"
}
