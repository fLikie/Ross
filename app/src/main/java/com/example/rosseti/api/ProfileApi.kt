package com.example.rosseti.api

import com.example.rosseti.data.SessionManager
import com.example.rosseti.data.UserData
import com.example.rosseti.data.mappers.LoginMappers.mapToUser
import com.example.rosseti.domain.entities.User
import com.google.gson.Gson
import javax.inject.Inject

class ProfileApi @Inject constructor(
    private val profileService: ProfileService,
    private val sessionManager: SessionManager,
    private val gson: Gson
    ) {

    suspend fun getProfile(): User {
        val json = profileService.getProfile(sessionManager.fetchAuthToken()).body()?.result
        return gson.fromJson(json, UserData::class.java).mapToUser()
    }
}