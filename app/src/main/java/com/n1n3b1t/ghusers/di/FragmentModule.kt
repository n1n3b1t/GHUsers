package com.n1n3b1t.ghusers.di

import com.n1n3b1t.ghusers.users.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by valentintaranenko on 02/01/2018.
 */
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeUserListFragment(): UserListFragment
}