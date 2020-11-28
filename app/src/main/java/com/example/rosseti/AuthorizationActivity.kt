package com.example.rosseti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.rosseti.api.LoginApi
import com.example.rosseti.api.ProfileApi
import com.example.rosseti.api.posts.LoginCredentials
import com.example.rosseti.data.SessionManager
import com.example.rosseti.presentation.AuthorizationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity(), AuthorizationView {

    @Inject lateinit var loginApi: LoginApi
    @Inject lateinit var profileApi: ProfileApi
    @Inject lateinit var sessionManager: SessionManager

    val context: CoroutineContext = SupervisorJob()
    val scope: CoroutineScope = CoroutineScope(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authtorization)
        scope.launch(Dispatchers.Default) {
            val token = loginApi.login(LoginCredentials("test", "rosseti12"))
            if (token.isNotBlank()) {
                sessionManager.saveAuthToken(token)
                val user = profileApi.getProfile()
                Log.i("maxresult", user.toString())
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