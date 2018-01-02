package com.n1n3b1t.ghusers.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.n1n3b1t.ghusers.R
import com.n1n3b1t.ghusers.databinding.ActivityLoginBinding
import com.n1n3b1t.ghusers.service.GithubOAuthService
import com.n1n3b1t.ghusers.users.MainActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by valentintaranenko on 29/12/2017.
 */
class LoginActivity : AppCompatActivity() {
    @Inject lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).viewModel = loginViewModel
        loginViewModel.uacToken.observe(this, Observer {
            it?.let {
                if (it.isNotEmpty())
                    startMainActivity()
            }
        })
        loginViewModel.loginAction.observe(this, Observer { it?.let { showLoginIntent() } })
    }

    fun showLoginIntent() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(GithubOAuthService.GITHUB_OAUTH)))
    }

    fun startMainActivity() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        val data = intent.data
        if (data != null && data.toString().startsWith(GithubOAuthService.GITHUB_CALLBACK)) {
            val token = data.getQueryParameter("code")
            if (token.isNotEmpty()) {
                loginViewModel.onCodeReceived(token)
            }
        }
    }
}