package com.aqube.mvi.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aqube.mvi.data.cache.models.ArticleEntity

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM articles")
    fun getBookmarkArticles(): List<ArticleEntity>

    @Query("DELETE FROM articles")
    fun clearArticles(): Int

    @Query("UPDATE articles SET is_bookmarked = 1 WHERE title = :articleTitle")
    fun bookmarkCharacter(articleTitle: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacter(vararg character: ArticleEntity)
}