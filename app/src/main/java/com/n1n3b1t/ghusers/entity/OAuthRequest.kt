package com.n1n3b1t.ghusers.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by valentintaranenko on 01/01/2018.
 */
data class OAuthRequest(@SerializedName("client_id") val clientId: String, @SerializedName("client_secret") val clientSecret: String, val code: String)