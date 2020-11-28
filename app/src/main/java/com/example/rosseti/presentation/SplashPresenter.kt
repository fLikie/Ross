package com.example.rosseti.presentation

import com.example.rosseti.data.LoginRepository
import com.example.rosseti.data.SessionManager
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
@ActivityScoped
class SplashPresenter @Inject constructor(
    private val sessionManager: SessionManager,
    private val loginRepository: LoginRepository
) : MvpPresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (checkToken()) {
            if (getProfile()) {
                viewState.goToMain()
            } else {
                viewState.goToLogin()
            }
        } else {
            viewState.goToLogin()
        }
    }


    private fun checkToken(): Boolean {
        return sessionManager.fetchAuthToken() != null
    }

    private fun getProfile(): Boolean {
        var success = false
        presenterScope.launch(Dispatchers.Default) {
            success = try {
                loginRepository.getProfile()
                true
            } catch (e: Exception) {
                false
            }
        }
        return success
    }
}