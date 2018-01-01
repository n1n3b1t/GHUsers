package com.n1n3b1t.ghusers.service

import com.n1n3b1t.ghusers.entity.OAuthRequest
import com.n1n3b1t.ghusers.entity.OAuthResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by valentintaranenko on 01/01/2018.
 */
interface GithubOAuthService {

    companion object {
        val GITHUB_CALLBACK = "ghusers://callback"
        val CLIENT_ID = "5e2c310bccdea4f2d74a"
        val CLIENT_SECRET = "bcce0f1f2e71feb2a4ab9728c6f827e1075feb20"
        val GITHUB_OAUTH = "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$GITHUB_CALLBACK"
        fun create(): GithubOAuthService {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }).build()
            return Retrofit.Builder().baseUrl("https://github.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(GithubOAuthService::class.java)
        }

        fun makeRequest(code: String) = OAuthRequest(CLIENT_ID, CLIENT_SECRET, code)
    }

    @Headers("Accept: application/json")
    @POST("/login/oauth/access_token")
    fun oAuth(@Body request: OAuthRequest): Single<OAuthResponse>
}