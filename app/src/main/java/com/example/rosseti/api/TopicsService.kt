package com.example.rosseti.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TopicsService {

    @GET("topics")
    fun getAllFavouriteTopics(@Header("token") token: String, @Query("interestings") interesings: Int = 1): Response<JsonObject>

    @GET("topics")
    fun getAllTopics(@Header("token") token: String, @Query("interestings") interesings: Int = 0): Response<JsonObject>
}