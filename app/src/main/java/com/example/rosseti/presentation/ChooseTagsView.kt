package com.example.rosseti.presentation

import com.example.rosseti.domain.entities.Topic
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ChooseTagsView : MvpView {
    fun showLoading(show: Boolean)
    fun loadTags(tags: List<Topic>)
    fun showToast(text: String)
}