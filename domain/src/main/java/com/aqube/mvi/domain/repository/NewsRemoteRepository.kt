package com.aqube.mvi.domain.repository

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRemoteRepository {
    suspend fun getTopHeadings(): Flow<Result<List<Article>>>
}