package com.aqube.mvi.di

import com.aqube.mvi.BuildConfig
import com.aqube.mvi.data.remote.api.NewsService
import com.aqube.mvi.data.remote.api.ServiceFactory
import com.aqube.mvi.data.remote.repository.ArticleRemoteRepositoryImpl
import com.aqube.mvi.data.remote.repository.CategoryRepositoryImpl
import com.aqube.mvi.domain.repository.ArticleRemoteRepository
import com.aqube.mvi.domain.repository.CategoryRepository
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
        return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL, BuildConfig.API_KEY)
    }

    @Provides
    @Singleton
    fun provideArticleRemoteRepository(newsRemoteRepository: ArticleRemoteRepositoryImpl): ArticleRemoteRepository {
        return newsRemoteRepository
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository {
        return categoryRepository
    }
}
