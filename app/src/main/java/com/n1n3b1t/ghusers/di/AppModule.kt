package com.n1n3b1t.ghusers.di

import android.app.Application
import com.n1n3b1t.ghusers.db.GithubDb
import com.n1n3b1t.ghusers.repository.RemoteUserRepository
import com.n1n3b1t.ghusers.repository.UserRepository
import com.n1n3b1t.ghusers.service.GithubService
import com.n1n3b1t.ghusers.util.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by valentintaranenko on 27/12/2017.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGHService(): GithubService = GithubService.instance

    @Singleton
    @Provides
    fun provideGHDb(app: Application): GithubDb = GithubDb.getInstance(app)

    @Singleton
    @Provides
    fun provideRemoteRepository(githubService: GithubService) = RemoteUserRepository(githubService)

    @Singleton
    @Provides
    fun provideUserRepo(githubDb: GithubDb, remoteUserRepository: RemoteUserRepository) = UserRepository(remoteUserRepository, githubDb.userDao())

    @Singleton
    @Provides
    fun providePrefs(app: Application): Prefs = Prefs(app)
}