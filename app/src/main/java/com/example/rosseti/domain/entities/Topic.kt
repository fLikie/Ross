package com.example.rosseti.domain.entities

import com.google.gson.annotations.SerializedName

data class Topic(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String
)