package com.example.rosseti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rosseti.presentation.SplashPresenter
import com.example.rosseti.presentation.SplashView
import dagger.hilt.android.AndroidEntryPoint
import moxy.presenter.InjectPresenter

@AndroidEntryPoint
class SplashScreen : AppCompatActivity(), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // проверка токена авторизации
        val intent = if (checkToken()) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, AuthorizationActivity::class.java)
        }

        startActivity(intent)
    }

    private fun checkToken(): Boolean {
        return false
    }
}