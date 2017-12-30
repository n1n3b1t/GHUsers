package com.n1n3b1t.ghusers.service

import com.n1n3b1t.ghusers.entity.User
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by valentintaranenko on 26/12/2017.
 */
interface GithubService {
    companion object {
        val instance by lazy {
            Retrofit.Builder().baseUrl("https://api.github.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GithubService::class.java)
        }
        val GITHUB_CALLBACK = "ghusers://callback"
        val client_id = "5e2c310bccdea4f2d74a"
        val GITHUB_OAUTH = "https://github.com/login/oauth/authorize?client_id=$client_id&redirect_uri=$GITHUB_CALLBACK"
    }

    @GET("/users")
    fun getAllUsers(@Query("since") since: Long? = null): Flowable<Response<List<User>>>
}