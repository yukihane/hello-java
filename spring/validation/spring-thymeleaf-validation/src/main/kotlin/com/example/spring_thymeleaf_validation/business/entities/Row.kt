package com.example.spring_thymeleaf_validation.business.entities

import jakarta.validation.constraints.Max

data class Row(
    var variety: Variety? = null,
    @field:Max(5)
    var seedsPerCell: Int? = null,
) {
    override fun toString(): String {
        return "Row [variety=$variety, seedsPerCell=$seedsPerCell]"
    }
}
