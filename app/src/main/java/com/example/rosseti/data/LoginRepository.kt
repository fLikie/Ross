package com.example.rosseti.data

import com.example.rosseti.api.ProfileApi
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val profileApi: ProfileApi
) {
    suspend fun getProfile() = profileApi.getProfile()

}