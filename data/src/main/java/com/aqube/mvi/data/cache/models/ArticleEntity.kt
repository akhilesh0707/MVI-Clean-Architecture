package com.aqube.mvi.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aqube.mvi.data.cache.CacheConstants
import java.io.Serializable

@Entity(tableName = CacheConstants.ARTICLES_TABLE_NAME)
data class ArticleEntity(
    @PrimaryKey
    val title: String,
    @Embedded
    val source: SourceEntity,
    val author: String,
    val content: String,
    val description: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    val url: String,
    @ColumnInfo(name = "url_to_image")
    val urlToImage: String,
    @ColumnInfo(name = "is_bookmarked")
    var isBookMarked: Boolean
) : Serializable