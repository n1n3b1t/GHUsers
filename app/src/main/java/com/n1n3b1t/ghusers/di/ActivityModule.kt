package com.n1n3b1t.ghusers.di

import com.n1n3b1t.ghusers.MainActivity
import com.n1n3b1t.ghusers.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by valentintaranenko on 27/12/2017.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
}