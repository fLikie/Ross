package com.example.rosseti.presentation

import dagger.hilt.android.scopes.ActivityScoped
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
@ActivityScoped
class SplashPresenter : MvpPresenter<SplashView>() {
}