package com.n1n3b1t.ghusers.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.n1n3b1t.ghusers.UserViewModel
import com.n1n3b1t.ghusers.base.ViewModelFactory
import com.n1n3b1t.ghusers.service.GithubOAuthService
import com.n1n3b1t.ghusers.util.Prefs
import javax.inject.Inject

/**
 * Created by valentintaranenko on 29/12/2017.
 */
class LoginViewModel @Inject constructor(val prefs: Prefs, val githubOAuthService: GithubOAuthService) : ViewModel() {

    val uacToken = prefs.uacTokenLiveData

    fun onCodeReceived(code: String) {
        githubOAuthService.oAuth(GithubOAuthService.makeRequest(code)).subscribe({ prefs.uacToken = it.token }, {})
    }

    companion object {
        fun create(activity: AppCompatActivity, factory: ViewModelFactory): UserViewModel = ViewModelProviders.of(activity, factory).get(UserViewModel::class.java)
    }
}