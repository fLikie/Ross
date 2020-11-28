package com.example.rosseti.data.mappers

import com.example.rosseti.data.UserData
import com.example.rosseti.domain.entities.User
import java.text.SimpleDateFormat
import java.util.*

object LoginMappers {
    fun UserData.mapToUser(): User = User(id, firstName, lastName, patronymic, birthdate.toDate(), position, organizationName, structureName, education, dateOfEmployment.toDate())

    private fun String.toDate(): Date {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return simpleDateFormat.parse(this)
    }
}