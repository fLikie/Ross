package com.example.rosseti.presentation

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AuthorizationView : MvpView {
    fun showToast(text: String)
    fun goToMain()
}