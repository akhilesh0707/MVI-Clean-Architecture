package com.aqube.mvi.domain.repository

import com.aqube.mvi.domain.model.NewsResponse
import com.aqube.mvi.domain.common.Result
interface ArticleRemoteRepository {
    suspend fun getTopArticles(
        pageSize: Int,
        pageNumber: Int,
        country: String
    ): Result<NewsResponse>

    suspend fun searchArticle(
        pageSize: Int,
        pageNumber: Int,
        searchQuery: String
    ): Result<NewsResponse>
}