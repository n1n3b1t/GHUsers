package com.n1n3b1t.ghusers.util

import android.content.Context
import com.n1n3b1t.ghusers.base.stringLiveData

/**
 * Created by valentintaranenko on 29/12/2017.
 */
class Prefs(context: Context) {
    val PREFS_NAME = "com.teleportfuturetechnologies.prefs"
    val UAC_TOKEN = "uac_token"
    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
    var uacToken: String?
        get() = prefs.getString(UAC_TOKEN, null)
        set(value) = prefs.edit().putString(UAC_TOKEN, value).apply()

    var uacTokenLiveData = prefs.stringLiveData(UAC_TOKEN, "")
}