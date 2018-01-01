package com.n1n3b1t.ghusers.di

import com.n1n3b1t.ghusers.service.APIService
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by valentintaranenko on 02/01/2018.
 */
@Module
abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract fun contributeAPIService(): APIService
}