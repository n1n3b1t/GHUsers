package com.n1n3b1t.ghusers.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.n1n3b1t.ghusers.users.UserViewModel
import com.n1n3b1t.ghusers.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by valentintaranenko on 27/12/2017.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(viewModel: UserViewModel): ViewModel

    @Binds
    abstract fun bindViewModel(factory: ViewModelFactory): ViewModelProvider.Factory
}