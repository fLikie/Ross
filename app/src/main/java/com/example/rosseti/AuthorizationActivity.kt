package com.example.rosseti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.rosseti.api.LoginApi
import com.example.rosseti.api.posts.LoginCredentials
import com.example.rosseti.presentation.AuthorizationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity(), AuthorizationView {

    @Inject lateinit var loginApi: LoginApi

    val context: CoroutineContext = SupervisorJob()
    val scope: CoroutineScope = CoroutineScope(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authtorization)
        scope.launch(Dispatchers.Default) {
            val token = loginApi.login(LoginCredentials("test", "rosseti12"))
            withContext(Dispatchers.Main) {
                if (token.isNotBlank()) {

                } else {

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            scope.cancel()
        }
    }

    override fun auth() {

    }
}