package com.n1n3b1t.ghusers.di

import android.app.Application
import com.n1n3b1t.ghusers.db.GithubDb
import com.n1n3b1t.ghusers.interactor.UserInteractor
import com.n1n3b1t.ghusers.repository.LocalUserRepository
import com.n1n3b1t.ghusers.repository.RemoteUserRepository
import com.n1n3b1t.ghusers.service.GithubOAuthService
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
    fun provideGHService(prefs: Prefs): GithubService = GithubService.create(prefs)

    @Singleton
    @Provides
    fun provideGHOauthService(): GithubOAuthService = GithubOAuthService.create()

    @Singleton
    @Provides
    fun provideGHDb(app: Application): GithubDb = GithubDb.getInstance(app)

    @Singleton
    @Provides
    fun provideRemoteRepository(githubService: GithubService) = RemoteUserRepository(githubService)

    @Singleton
    @Provides
    fun provideUserRepo(githubDb: GithubDb, remoteUserRepository: RemoteUserRepository) = LocalUserRepository(remoteUserRepository, githubDb.userDao())

    @Singleton
    @Provides
    fun providePrefs(app: Application): Prefs = Prefs(app)

    @Singleton
    @Provides
    fun provideUserInteractor(app: Application, localUserRepository: LocalUserRepository, remoteUserRepository: RemoteUserRepository)
            = UserInteractor(localUserRepository, remoteUserRepository, app)
}