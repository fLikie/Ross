package com.example.rosseti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.rosseti.api.LoginApi
import com.example.rosseti.api.ProfileApi
import com.example.rosseti.api.posts.LoginCredentials
import com.example.rosseti.data.SessionManager
import com.example.rosseti.presentation.AuthorizationPresenter
import com.example.rosseti.presentation.AuthorizationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_authtorization.*
import kotlinx.coroutines.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class AuthorizationActivity : MvpAppCompatActivity(), AuthorizationView {

    @Inject lateinit var loginApi: LoginApi
    @Inject lateinit var profileApi: ProfileApi
    @Inject lateinit var sessionManager: SessionManager

    @Inject
    lateinit var daggerPresenter: AuthorizationPresenter

    @InjectPresenter
    lateinit var presenter: AuthorizationPresenter

    @ProvidePresenter
    fun providePresenter() = daggerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authtorization)
        sign_in.setOnClickListener {
            presenter.login(login_edit_text.text.toString().trim(), password_edit_text.text.toString().trim())
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}