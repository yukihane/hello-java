package com.example.spring_thymeleaf_validation.business.entities

import java.util.Arrays
import java.util.Date

data class SeedStarter (
    var id: Int? = null,
    var datePlanted: Date? = null,
    var covered: Boolean? = null,
    var type: Type = Type.PLASTIC,
    var features: Array<Feature>? = null,
    val rows: MutableList<Row> = ArrayList(),
){
    override fun toString(): String {
        return "SeedStarter [id=$id, datePlanted=$datePlanted, " +
                "covered=$covered, type=$type, features=${Arrays.toString(features)}, rows=$rows]"
    }
}
