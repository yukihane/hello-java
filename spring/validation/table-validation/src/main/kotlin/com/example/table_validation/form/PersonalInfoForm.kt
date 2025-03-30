package com.example.table_validation.form

import com.example.table_validation.model.PersonalInfo
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty

data class PersonalInfoForm(
    @field:NotEmpty(message = "少なくとも1つの個人情報が必要です")
    @field:Valid
    var infoList: MutableList<PersonalInfo> = mutableListOf()
)
