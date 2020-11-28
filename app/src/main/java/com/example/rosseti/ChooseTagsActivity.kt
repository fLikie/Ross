package com.example.rosseti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rosseti.presentation.ChooseTagsPresenter
import com.example.rosseti.presentation.ChooseTagsView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_choose_tags.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class ChooseTagsActivity : MvpAppCompatActivity(), ChooseTagsView {

    @Inject
    lateinit var daggerPresenter: ChooseTagsPresenter

    @InjectPresenter
    lateinit var presenter: ChooseTagsPresenter

    @ProvidePresenter
    fun providePresenter() = daggerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_tags)
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}