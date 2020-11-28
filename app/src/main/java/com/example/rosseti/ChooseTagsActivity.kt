package com.example.rosseti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rosseti.presentation.ChooseTagsPresenter
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class ChooseTagsActivity : MvpAppCompatActivity() {

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


}