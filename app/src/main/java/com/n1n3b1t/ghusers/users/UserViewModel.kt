package com.n1n3b1t.ghusers.users

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.n1n3b1t.ghusers.base.ViewModelFactory
import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.interactor.UserInteractor
import com.n1n3b1t.ghusers.util.AbsentLiveData
import javax.inject.Inject

/**
 * Created by valentintaranenko on 27/12/2017.
 */
class UserViewModel @Inject constructor(private val userInteractor: UserInteractor) : ViewModel() {
    init {
        userInteractor.startFetchService()
    }

    val query = MutableLiveData<String>()
    val results = Transformations.switchMap(query) {
        if (it.isBlank()) {
            return@switchMap AbsentLiveData<List<User>>()
        } else {
            return@switchMap userInteractor.findUser(it)
        }
    }


    fun onQuery(query: String) {
        this.query.value = query
    }

    companion object {
        fun create(activity: AppCompatActivity, factory: ViewModelFactory): UserViewModel = ViewModelProviders.of(activity, factory).get(UserViewModel::class.java)
    }
}