package com.example.rosseti.api

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("result") val result: JsonObject?,
    @SerializedName("status") val status: String?
)