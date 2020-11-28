package com.example.rosseti.domain.entities

import com.example.rosseti.Education
import java.util.*

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val patronymic: String?, //отчество
    val birthdate: Date,
    val position: String,
    val organizationName: String,
    val structureName: String,
    val education: Education,
    val dateOfEmployment: Date, // когда начал работать в электрике
) {
    val fullName: String
        get() = "$lastName $firstName" + if (!patronymic.isNullOrBlank()) " $patronymic" else ""
}