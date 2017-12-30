package com.n1n3b1t.ghusers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import com.n1n3b1t.ghusers.base.ViewModelFactory
import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.repository.UserRepository
import javax.inject.Inject

/**
 * Created by valentintaranenko on 27/12/2017.
 */
class UserViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    fun getUsers(): LiveData<List<User>> {
        return userRepository.getUsers('a', 'f')
    }

    companion object {
        fun create(activity: MainActivity, factory: ViewModelFactory): UserViewModel = ViewModelProviders.of(activity, factory).get(UserViewModel::class.java)
    }
}