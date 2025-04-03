package com.example.table_validation.model

import jakarta.validation.constraints.*

data class PersonalInfo(
    @field:NotBlank(message = "名前は必須です")
    @field:Pattern(regexp = "\\d{1,10}", message = "名前は1〜10文字の数字で入力してください")
    @field:Size(max = 5, message = "名前は50文字以内で入力してください")
    var name: String = "",

    @field:NotNull(message = "年齢は必須です")
    @field:Min(value = 0, message = "年齢は0以上で入力してください")
    @field:Max(value = 120, message = "年齢は120以下で入力してください")
    var age: Int? = null,

    @field:NotBlank(message = "住所は必須です")
    @field:Size(max = 100, message = "住所は100文字以内で入力してください")
    var address: String = ""
) {
    constructor() : this("", null, "")
}
