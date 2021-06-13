package com.aqube.mvi.data.remote.repository

import com.aqube.mvi.data.remote.api.NewsService
import com.aqube.mvi.domain.common.CallErrors
import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.articles.NewsResponse
import com.aqube.mvi.domain.repository.ArticleRemoteRepository
import retrofit2.Response
import javax.inject.Inject

class ArticleRemoteRepositoryImpl @Inject constructor(private val newsService: NewsService) :
    ArticleRemoteRepository {
    override suspend fun getTopArticles(
        pageSize: Int,
        pageNumber: Int,
        country: String
    ): Result<NewsResponse> {
        return getResult(newsService.getTopHeadings(pageSize, pageNumber, country))
    }

    override suspend fun searchArticle(
        pageSize: Int,
        pageNumber: Int,
        searchQuery: String
    ): Result<NewsResponse> {
        return getResult(newsService.searchArticles(pageSize, pageNumber, searchQuery))
    }

    override suspend fun categoryArticle(
        pageSize: Int,
        pageNumber: Int,
        country: String,
        category: String
    ): Result<NewsResponse> {
        return getResult(newsService.getCategoryArticles(pageSize, pageNumber, country, category))
    }

    private fun getResult(result: Response<NewsResponse>): Result<NewsResponse> {
        return try {
            if (result.isSuccessful) {
                val resultBody = result.body()
                if (resultBody != null) {
                    Result.Success(resultBody)
                } else {
                    Result.Error(CallErrors.ErrorEmptyData)
                }
            } else {
                Result.Error(CallErrors.ErrorException(Exception("Code: ${result.code()} - Error: ${result.message()}")))
            }
        } catch (e: Exception) {
            Result.Error(CallErrors.ErrorException(e))
        }
    }
}