package com.example.kotlin_form_binding

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE
import java.time.LocalDate

data class DataClassVarForm(
    /** 名前(必須) */
    @field:NotNull
    @field:NotEmpty
    var name: String? = null,

    /** 生年月日(必須) */
    @field:NotNull
    @field:DateTimeFormat(iso = DATE)
    var birthDate: LocalDate? = null,

    /** 年齢(任意) */
    var age: Short? = null,

    /** 家族 */
    @field:Valid
    @field:NotNull
    var families: List<DataClassFamilyVar> = emptyList(),
)

data class DataClassFamilyVar(
    /** 家族の名前(必須) */
    @field:NotNull
    @field:NotEmpty
    var familyName: String? = null,
)
