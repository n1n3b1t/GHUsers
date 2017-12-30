package com.n1n3b1t.ghusers.repository

import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.service.GithubService
import com.n1n3b1t.ghusers.util.d
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by valentintaranenko on 26/12/2017.
 */
class RemoteUserRepository @Inject constructor(val githubService: GithubService) {

    fun getUsers(lastUser: User): Flowable<List<User>> {
        var last = lastUser
        return githubService.getAllUsers(last.id).concatMap {
            githubService.getAllUsers(last.id)
        }.doOnEach {
            it.value?.body()?.forEach { d("User #${it.id}") }
            it.value?.body()?.last()?.let {
                last = it
            }
        }.repeatUntil { lastUser.id > 200 }.map { it.body() }
    }

}