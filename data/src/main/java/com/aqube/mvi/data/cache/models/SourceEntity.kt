package com.aqube.mvi.data.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aqube.mvi.data.cache.CacheConstants

@Entity(tableName = CacheConstants.ARTICLE_SOURCE_TABLE_NAME)
data class SourceEntity(
    @PrimaryKey
    val name: String,
    val id: String
)