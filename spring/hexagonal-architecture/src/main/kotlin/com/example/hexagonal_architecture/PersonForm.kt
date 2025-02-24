package com.example.hexagonal_architecture

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class PersonForm(
    @field:NotNull @field:Size(min = 2, max = 30)
    val name: String?,

    @field:NotNull @field:Min(18)
    val age: Int?,
) {
    override fun toString(): String {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")"
    }
}
