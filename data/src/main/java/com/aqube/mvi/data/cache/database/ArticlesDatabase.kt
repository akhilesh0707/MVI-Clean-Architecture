package com.aqube.mvi.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aqube.mvi.data.cache.Migrations
import com.aqube.mvi.data.cache.dao.ArticlesDao
import com.aqube.mvi.data.cache.models.ArticleEntity
import com.aqube.mvi.data.cache.models.SourceEntity
import javax.inject.Inject

@Database(
    entities = [ArticleEntity::class, SourceEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class ArticlesDatabase @Inject constructor() : RoomDatabase() {
    abstract fun cachedArticlesDao(): ArticlesDao
}