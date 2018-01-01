package com.n1n3b1t.ghusers.repository

import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.service.GithubService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by valentintaranenko on 26/12/2017.
 */
class RemoteUserRepository @Inject constructor(private val githubService: GithubService) {

    fun getUsers(since: Long?): Single<List<User>> {
        return githubService.getAllUsers(since)
                .flatMapObservable { Observable.fromIterable(it.body()) }
                .flatMap { githubService.getUser(it.login).map { it.body()!! }
                        .toObservable() }
                .toList()
    }

}