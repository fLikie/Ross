package com.example.rosseti

import android.content.Intent
import android.os.Bundle
import com.example.rosseti.presentation.SplashPresenter
import com.example.rosseti.presentation.SplashView
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class SplashScreen : MvpAppCompatActivity(), SplashView {

    @Inject
    lateinit var presenterProvider: Provider<SplashPresenter>

    @ProvidePresenter
    fun providePresenter(): SplashPresenter = presenterProvider.get()

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun goToLogin() {
        startActivity(Intent(this, AuthorizationActivity::class.java))
    }
}