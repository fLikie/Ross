package com.example.rosseti.data

import com.example.rosseti.Education
import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val firstName: String,
    @SerializedName("surname") val lastName: String,
    @SerializedName("patronymic") val patronymic: String?, //отчество
    @SerializedName("date_of_birth") val birthdate: String,
    @SerializedName("post") val position: String,
    @SerializedName("organization") val organizationName: String,
    @SerializedName("structure") val structureName: String,
    @SerializedName("education") val education: Education,
    @SerializedName("date_of_employment") val dateOfEmployment: String, // когда начал работать в электрике
)