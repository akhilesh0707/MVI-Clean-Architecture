package com.aqube.mvi.domain.repository

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.articles.NewsResponse

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

    suspend fun categoryArticle(
        pageSize: Int,
        pageNumber: Int,
        country: String,
        category: String
    ): Result<NewsResponse>
}