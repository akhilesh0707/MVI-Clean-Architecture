package com.aqube.mvi.di

import androidx.viewbinding.BuildConfig
import com.aqube.mvi.data.remote.api.NewsService
import com.aqube.mvi.data.remote.api.ServiceFactory
import com.aqube.mvi.data.remote.repository.NewsRemoteRepository
import com.aqube.mvi.data.remote.repository.NewsRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideNewsService(): NewsService {
        // return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
        return ServiceFactory.create(BuildConfig.DEBUG, "https://newsapi.org/v2/")
    }

    @Provides
    @Singleton
    fun provideNewsRemoteRepository(newsRemoteRepository: NewsRemoteRepositoryImpl): NewsRemoteRepository {
        return newsRemoteRepository
    }

}
