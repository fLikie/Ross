package com.example.rosseti.api

import com.example.rosseti.data.SessionManager
import com.example.rosseti.domain.entities.Topic
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class TopicsApi @Inject constructor(private val topicsService: TopicsService, private val sessionManager: SessionManager, private val gson: Gson) {

    suspend fun getAllTopics(): List<Topic> {
        val body = topicsService.getAllTopics(sessionManager.fetchAuthToken()!!).body()
        return if (body?.has("topics") == true) {
            val type = object : TypeToken<List<Topic>>() {}.type
            gson.fromJson(body["topics"], type)
        } else {
            emptyList()
        }
    }
}