package com.n1n3b1t.ghusers.di

import android.app.Application
import com.n1n3b1t.ghusers.GHApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by valentintaranenko on 27/12/2017.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent {
    fun inject(app: GHApp)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}