package com.example.rosseti.domain.entities

import com.example.rosseti.data.UserData
import com.google.gson.JsonObject
import java.text.SimpleDateFormat
import java.util.*

data class User(var id: Long,
                var firstName: String,
                var lastName: String,
                var patronymic: String?, //отчество
                var birthdate: Date,
                var position: String,
                var organizationName: String,
                var structureName: String,
                var education: String,
                var dateOfEmployment: Date) {

    constructor() : this(0, "", "", null, Date(), "", "", "", "", Date())

    val fullName: String
        get() = "$lastName $firstName" + if (!patronymic.isNullOrBlank()) " $patronymic" else ""

    fun inflateFromUserData(userData: UserData) {
        this.id = userData.id
        this.firstName = userData.firstName
        this.lastName = userData.lastName
        this.patronymic = userData.patronymic
        this.birthdate = userData.birthdate.toDate()
        this.position = userData.position.getName()
        this.organizationName = userData.organizationName.getName()
        this.structureName = userData.structureName.getName()
        this.education = userData.education.getName()
        this.dateOfEmployment = userData.dateOfEmployment.toDate()
    }

    private fun String.toDate(): Date {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return simpleDateFormat.parse(this)
    }

    private fun JsonObject.getName(): String = get("name").asString
}