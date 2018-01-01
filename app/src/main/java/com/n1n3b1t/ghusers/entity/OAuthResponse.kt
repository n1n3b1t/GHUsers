package com.n1n3b1t.ghusers.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by valentintaranenko on 01/01/2018.
 */
data class OAuthResponse(@SerializedName("access_token") val token: String, @SerializedName("token_type") val type: String)