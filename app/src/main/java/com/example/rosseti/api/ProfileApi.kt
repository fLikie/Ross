package com.example.rosseti.api

import com.example.rosseti.data.SessionManager
import com.example.rosseti.data.UserData
import com.example.rosseti.domain.entities.User
import com.google.gson.Gson
import javax.inject.Inject

class ProfileApi @Inject constructor(
    private val profileService: ProfileService,
    private val sessionManager: SessionManager,
    private val gson: Gson,
    private val user: User
    ) {

    suspend fun getProfile(): User {
        val jsonBody = profileService.getProfile(sessionManager.fetchAuthToken()).body()
        if (jsonBody?.has("profile") == true) {
            user.inflateFromUserData(gson.fromJson(jsonBody.getAsJsonObject("profile"), UserData::class.java))
        } else {
            error("Не удалось получить профиль")
        }
        return user

    }
}