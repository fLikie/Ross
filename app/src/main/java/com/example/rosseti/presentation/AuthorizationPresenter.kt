package com.example.rosseti.presentation

import com.example.rosseti.api.LoginApi
import com.example.rosseti.api.posts.LoginCredentials
import com.example.rosseti.data.SessionManager
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
@ActivityScoped
class AuthorizationPresenter @Inject constructor(
    private val loginApi: LoginApi,
    private val sessionManager: SessionManager
) : MvpPresenter<AuthorizationView>() {

    fun login(login: String, password: String) {
        if (login.isEmpty()) {
            viewState.showToast("Enter login")
        }
        if (password.isEmpty()) {
            viewState.showToast("Enter password")
        }
        viewState.showLoading(true)
        presenterScope.launch(Dispatchers.Default) {
            val token = loginApi.login(LoginCredentials(login, password))
            withContext(Dispatchers.Main) {
                viewState.showLoading(false)
                if (token.isNotBlank()) {
                    sessionManager.saveAuthToken(token)
                    viewState.goToMain()
                } else {
                    viewState.showToast("Incorrect login or password")
                }
            }
        }
    }
}