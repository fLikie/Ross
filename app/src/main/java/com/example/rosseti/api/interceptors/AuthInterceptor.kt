package com.example.rosseti.api.interceptors

import com.example.rosseti.data.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.also {
            requestBuilder.addHeader("token", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}