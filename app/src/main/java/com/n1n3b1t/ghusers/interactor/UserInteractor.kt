package com.n1n3b1t.ghusers.interactor

import android.arch.lifecycle.LiveData
import android.content.Context
import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.repository.LocalUserRepository
import com.n1n3b1t.ghusers.repository.RemoteUserRepository
import com.n1n3b1t.ghusers.service.APIService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by valentintaranenko on 02/01/2018.
 */
class UserInteractor @Inject constructor(val localUserRepository: LocalUserRepository, val remoteUserRepository: RemoteUserRepository, val context: Context) {

    fun startFetchService() {
        APIService.fetchUsers(context)
    }

    fun getUsers(startsFrom: Char, endsWith: Char): LiveData<List<User>> {
        return localUserRepository.getUsers(startsFrom, endsWith)
    }

    fun fetchUsers(): Single<List<User>> {
        return localUserRepository.getLastUser()
                .map { it.id }
                .onErrorResumeNext { Single.just(0) }
                .flatMap { remoteUserRepository.getUsers(it) }
                .doOnSuccess { localUserRepository.addUsers(it) }
    }

}