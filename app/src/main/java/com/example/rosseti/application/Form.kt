package com.example.rosseti.application

import com.example.rosseti.domain.entities.User
import java.util.*

data class Form(
    var applicationRegNumber: Long = 123456,
    var applicationRegDate: Date = Date(),
    var authors: MutableList<User> = mutableListOf(),
    var shortname: String = "",
    var categories: List<CategoryOfDigitalTransf> = emptyList(),
    var issueDescription: String = "",
    var issueSolution: String = "",
    var issueEffect: String = "",
    var expenditures: List<Spending> = emptyList(),
    var steps: List<ImplementationStep> = emptyList(),
    var doesSaveMoney: Boolean = false,
    var rewards: MutableMap<User, String> = mutableMapOf()
)