package com.example.rosseti.presentation

import com.example.rosseti.api.TopicsApi
import com.example.rosseti.data.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
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
        presenterScope.launch (Dispatchers.Default) {
            val topics = topicsApi.getAllTopics()
            withContext(Dispatchers.Main) {
                viewState.showLoading(false)
                if (topics.isNotEmpty()) {
                    viewState.loadTags(topics)
                } else {
                    viewState.showToast("Problems was occured")
                }
            }
        }
    }
}