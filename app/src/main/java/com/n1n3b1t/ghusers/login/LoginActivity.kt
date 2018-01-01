package com.n1n3b1t.ghusers.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.n1n3b1t.ghusers.MainActivity
import com.n1n3b1t.ghusers.R
import com.n1n3b1t.ghusers.service.GithubOAuthService
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
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.but_login).setOnClickListener { showLoginIntent() }
        loginViewModel.uacToken.observe(this, Observer {
            it?.let {
                if (it.isNotEmpty())
                    startMainActivity()
            }
        })
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