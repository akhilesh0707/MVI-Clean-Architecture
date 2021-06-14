package com.aqube.mvi.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aqube.mvi.data.cache.CacheConstants
import java.io.Serializable

@Entity(tableName = CacheConstants.ARTICLES_TABLE_NAME)
data class ArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded
    val sourceEntity: SourceEntity,
    @PrimaryKey
    val title: String,
    val url: String,
    val urlToImage: String,
    @ColumnInfo(name = "is_bookmarked")
    var isBookMarked: Boolean
) : Serializable