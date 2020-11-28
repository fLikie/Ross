package com.example.rosseti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rosseti.presentation.AuthorizationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity(), AuthorizationView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authtorization)
    }

    override fun auth() {

    }
}