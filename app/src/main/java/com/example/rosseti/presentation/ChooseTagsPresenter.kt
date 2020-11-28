package com.example.rosseti.presentation

import com.example.rosseti.api.TopicsApi
import com.example.rosseti.data.SessionManager
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ChooseTagsPresenter @Inject constructor(
    private val sessionManager: SessionManager,
    private val topicsApi: TopicsApi
) : MvpPresenter<ChooseTagsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(true)
        loadTags()
    }

    private fun loadTags() {

    }
}