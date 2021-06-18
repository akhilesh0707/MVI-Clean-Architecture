package com.aqube.mvi.di

import android.content.Context
import androidx.room.Room
import com.aqube.mvi.data.cache.CacheConstants
import com.aqube.mvi.data.cache.dao.ArticlesDao
import com.aqube.mvi.data.cache.database.ArticlesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): ArticlesDatabase {
        return Room.databaseBuilder(
            context,
            ArticlesDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }

    @Provides
    fun provideDataAccessObject(database: ArticlesDatabase): ArticlesDao =
        database.cachedArticlesDao()
}
