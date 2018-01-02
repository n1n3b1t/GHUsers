package com.n1n3b1t.ghusers.users

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.n1n3b1t.ghusers.base.ViewModelFactory
import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.interactor.UserInteractor
import javax.inject.Inject

/**
 * Created by valentintaranenko on 27/12/2017.
 */
class UserViewModel @Inject constructor(val userInteractor: UserInteractor) : ViewModel() {


    fun getUsers(): LiveData<List<User>> {
        userInteractor.startFetchService()
        return userInteractor.getUsers('a', 'z')
    }

    companion object {
        fun create(activity: AppCompatActivity, factory: ViewModelFactory): UserViewModel = ViewModelProviders.of(activity, factory).get(UserViewModel::class.java)
    }
}