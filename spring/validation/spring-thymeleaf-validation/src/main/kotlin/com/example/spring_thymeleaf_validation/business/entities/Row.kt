package com.example.spring_thymeleaf_validation.business.entities

data class Row (
    var variety: Variety? = null,
    var seedsPerCell: Int? = null,
){
    override fun toString(): String {
        return "Row [variety=$variety, seedsPerCell=$seedsPerCell]"
    }
}
