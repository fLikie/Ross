package com.example.rosseti.api

import com.example.rosseti.data.SessionManager
import javax.inject.Inject

class TopicsApi @Inject constructor(private val topicsService: TopicsService, private val sessionManager: SessionManager) {

    fun getAllTopics() = topicsService.getAllTopics(sessionManager.fetchAuthToken()!!)
}