package com.n1n3b1t.ghusers.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by valentintaranenko on 26/12/2017.
 */
@Entity data class User(@PrimaryKey val id: Long, val login: String, val followers: Int, val following: Int, @SerializedName("avatar_url") val avatar: String)