package com.example.rosseti.api

import com.example.rosseti.api.posts.LoginCredentials
import com.google.gson.Gson
import javax.inject.Inject

class LoginApi @Inject constructor(private val loginService: LoginService, private val gson: Gson) {

    suspend fun login(loginCredentials: LoginCredentials): String {
        val body = loginService.login(loginCredentials).body()
        return if (body != null && body.has("token")) {
            body.get("token").asString
        } else {
            ""
        }
    }
}