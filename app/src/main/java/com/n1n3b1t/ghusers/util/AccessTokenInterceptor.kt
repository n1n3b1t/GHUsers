package com.n1n3b1t.ghusers.util

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by valentintaranenko on 31/12/2017.
 */
class AccessTokenInterceptor(val prefs: Prefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (prefs.uacToken != null && prefs.uacToken?.isNotEmpty()!!) {
            val newRequest = request.newBuilder().header("Authorization", "token ${prefs.uacToken}").build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(request)
    }
}