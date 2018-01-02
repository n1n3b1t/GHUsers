package com.n1n3b1t.ghusers.util

import android.arch.lifecycle.LiveData

/**
 * Created by valentintaranenko on 02/01/2018.
 */
class AbsentLiveData<T> : LiveData<T>() {
    init {
        postValue(null)
    }
}