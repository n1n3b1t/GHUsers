package com.n1n3b1t.ghusers.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.n1n3b1t.ghusers.interactor.UserInteractor
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by valentintaranenko on 02/01/2018.
 */
class APIService : IntentService("APIService") {
    @Inject lateinit var userInteractor: UserInteractor
    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    companion object {
        val TYPE = "type"
        val FETCH = "fetch"
        fun fetchUsers(context: Context) {
            context.startService(Intent(context, APIService::class.java).apply { putExtra(TYPE, FETCH) })
        }
    }

    override fun onHandleIntent(intent: Intent) {
        when (intent.getStringExtra(TYPE)) {
            FETCH -> userInteractor.fetchUsers().blockingGet()
        }
    }
}