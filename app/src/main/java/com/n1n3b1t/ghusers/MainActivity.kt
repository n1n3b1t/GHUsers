package com.n1n3b1t.ghusers

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.n1n3b1t.ghusers.base.ViewModelFactory
import com.n1n3b1t.ghusers.util.d
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UserViewModel.create(this, viewModelFactory).getUsers().observe(this, Observer { it?.forEach { Log.d("User", "${it.login} ${it.avatar} followers, ${it.following} following ${it.followers}") } })
    }

    override fun onResume() {
        super.onResume()
        val data = intent.data
        if (data != null && data.toString().startsWith("ghusers")) {
            val code = data.getQueryParameter("code")
            d("Code is $code")
        }
    }
}
