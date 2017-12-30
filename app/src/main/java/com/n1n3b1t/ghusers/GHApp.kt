package com.n1n3b1t.ghusers

import android.app.Activity
import android.app.Application
import com.n1n3b1t.ghusers.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by valentintaranenko on 27/12/2017.
 */
class GHApp : Application(), HasActivityInjector {
    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}