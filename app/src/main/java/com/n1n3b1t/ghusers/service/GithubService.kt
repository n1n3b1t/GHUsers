package com.n1n3b1t.ghusers.service

import com.n1n3b1t.ghusers.entity.User
import com.n1n3b1t.ghusers.util.AccessTokenInterceptor
import com.n1n3b1t.ghusers.util.Prefs
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by valentintaranenko on 26/12/2017.
 */
interface GithubService {
    companion object {
        fun create(prefs: Prefs): GithubService {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(AccessTokenInterceptor(prefs)).addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }).build()
            return Retrofit.Builder().baseUrl("https://api.github.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(GithubService::class.java)

        }


    }

    @GET("/users")
    fun getAllUsers(@Query("since") since: Long? = null): Single<Response<List<User>>>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Single<Response<User>>
}