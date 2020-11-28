package com.example.rosseti.presentation

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SplashView : MvpView {
    fun goToMain()
    fun goToLogin()
}