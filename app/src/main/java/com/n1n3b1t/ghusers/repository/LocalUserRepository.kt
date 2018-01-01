package com.n1n3b1t.ghusers.repository

import android.arch.lifecycle.LiveData
import com.n1n3b1t.ghusers.dao.UserDao
import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.util.d
import com.n1n3b1t.ghusers.util.e
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by valentintaranenko on 26/12/2017.
 */
class LocalUserRepository @Inject constructor(private val remoteUserRepository: RemoteUserRepository, private val userDao: UserDao) {

    fun getUsers(): LiveData<List<User>> {

        return userDao.getUsers()
    }

    fun getUsers(startsFrom: Char, endsWith: Char): LiveData<List<User>> {
        try {
            var result = userDao.getUsers("[$startsFrom-$endsWith]*")
            return result
        } catch (e: Throwable) {
            e("Error while getting ", e)
        }
        throw error("Error while getting")
    }

    fun getLastUser(): Single<User> {
        return userDao.getLastUser()
    }

    fun addUsers(users: List<User>) {
        userDao.insertAll(users)
    }

}