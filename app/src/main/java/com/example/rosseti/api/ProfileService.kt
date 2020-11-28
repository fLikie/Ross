package com.example.rosseti.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {
    @GET("get_profile")
    suspend fun getProfile(@Header("token") token: String?): Response<com.example.rosseti.api.Response>
}