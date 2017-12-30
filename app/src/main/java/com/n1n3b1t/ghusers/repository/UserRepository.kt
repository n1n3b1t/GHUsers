package com.n1n3b1t.ghusers.repository

import android.arch.lifecycle.LiveData
import com.n1n3b1t.ghusers.dao.UserDao
import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.util.d
import com.n1n3b1t.ghusers.util.e
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by valentintaranenko on 26/12/2017.
 */
class UserRepository @Inject constructor(val remoteUserRepository: RemoteUserRepository, val userDao: UserDao) {

    fun getUsers(): LiveData<List<User>> {
        d("Get users")
        fetchUsers()
        return userDao.getUsers()
    }

    fun getUsers(startsFrom: Char, endsWith: Char): LiveData<List<User>> {
        fetchUsers()
        val filters = CharRange(startsFrom, endsWith).map { "$it'%'" }.toTypedArray()
        filters.forEach { d("Filter $it") }
        try {
            var result = userDao.getUsers("[$startsFrom-$endsWith]*")
            return result
        } catch (e: Throwable) {
            e("Error while getting ", e)
        }
        throw error("Error while getting")
    }

    private fun fetchUsers(count: Long = 45) {
        userDao.getLastUser().flatMap { remoteUserRepository.getUsers(it.first()) }.flatMapIterable { it }.repeat().take(count).toList().observeOn(Schedulers.io()).subscribe({
            d("Got ${it.size} users")
            try {
                userDao.insertAll(it)
            } catch (e: Throwable) {
                e("Error while inserting", e)
            }
        }, {})
    }

}