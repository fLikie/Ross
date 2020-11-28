package com.example.rosseti.data

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val firstName: String,
    @SerializedName("surname") val lastName: String,
    @SerializedName("patronymic") val patronymic: String?, //отчество
    @SerializedName("date_of_birth") val birthdate: String,
    @SerializedName("post") val position: JsonObject,
    @SerializedName("organization") val organizationName: JsonObject,
    @SerializedName("structure") val structureName: JsonObject,
    @SerializedName("education") val education: JsonObject,
    @SerializedName("date_of_employment") val dateOfEmployment: String, // когда начал работать в электрике
)