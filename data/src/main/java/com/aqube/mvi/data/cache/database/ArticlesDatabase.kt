package com.aqube.mvi.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aqube.mvi.data.cache.CacheConstants
import com.aqube.mvi.data.cache.Migrations
import com.aqube.mvi.data.cache.dao.ArticlesDao
import com.aqube.mvi.data.cache.models.ArticleEntity
import javax.inject.Inject
import javax.xml.transform.Source

@Database(
    entities = [ArticleEntity::class, Source::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class ArticlesDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedArticlesDao(): ArticlesDao

    companion object {
        @Volatile
        private var INSTANCE: ArticlesDatabase? = null

        fun getInstance(context: Context): ArticlesDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticlesDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}