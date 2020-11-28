package com.example.rosseti.presentation

import com.example.rosseti.data.SessionManager
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ChooseTagsPresenter @Inject constructor(
    private val sessionManager: SessionManager
) : MvpPresenter<ChooseTagsView>() {

}