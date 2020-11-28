package com.example.rosseti.api

import com.example.rosseti.api.posts.LoginCredentials
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    @Headers("Content-type: application/json")
    suspend fun login(@Body loginCredentials: LoginCredentials): Response<JsonObject>
}