package com.example.rosseti.presentation

import dagger.hilt.android.scopes.ActivityScoped
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
@ActivityScoped
class AuthorizationPresenter @Inject constructor() : MvpPresenter<AuthorizationView>() {
}