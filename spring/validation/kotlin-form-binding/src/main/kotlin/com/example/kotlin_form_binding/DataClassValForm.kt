package com.example.kotlin_form_binding

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO.DATE
import java.time.LocalDate

data class DataClassValForm(
    /** 名前(必須) */
    @field:NotNull
    @field:NotEmpty
    val name: String,

    /** 生年月日(必須) */
    @field:NotNull
    @field:DateTimeFormat(iso = DATE)
    val birthDate: LocalDate,

    /** 年齢(任意) */
    val age: Short?,

    /** 家族 */
    @field:Valid
    @field:NotNull
    val families: List<DataClassFamily> = emptyList(),
)

data class DataClassFamily(
    /** 家族の名前(必須) */
    @field:NotNull
    @field:NotEmpty
    val familyName: String,
)
