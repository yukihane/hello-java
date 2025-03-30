package com.example.spring_thymeleaf_validation.business.entities

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import java.util.*

data class SeedStarter(
    var id: Int? = null,
    var datePlanted: Date? = null,
    var covered: Boolean? = null,
    var type: Type = Type.PLASTIC,
    var features: Array<Feature>? = null,
    @field:Valid
    @field:NotNull
    val rows: MutableList<Row> = ArrayList(),
) {
    override fun toString(): String {
        return "SeedStarter [id=$id, datePlanted=$datePlanted, " +
                "covered=$covered, type=$type, features=${Arrays.toString(features)}, rows=$rows]"
    }
}
