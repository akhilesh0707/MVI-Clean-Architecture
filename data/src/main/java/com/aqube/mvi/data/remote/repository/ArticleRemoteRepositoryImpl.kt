package com.aqube.mvi.data.remote.repository

import com.aqube.mvi.data.remote.api.NewsService
import com.aqube.mvi.domain.common.CallErrors
import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.NewsResponse
import com.aqube.mvi.domain.repository.ArticleRemoteRepository
import javax.inject.Inject

class ArticleRemoteRepositoryImpl @Inject constructor(private val newsService: NewsService) :
    ArticleRemoteRepository {
    override suspend fun getTopArticles(
        pageSize: Int,
        pageNumber: Int,
        country: String
    ): Result<NewsResponse> {
        return try {
            val result = newsService.getTopHeadings(pageSize, pageNumber, country)
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

    override suspend fun searchArticle(
        pageSize: Int,
        pageNumber: Int,
        searchQuery: String
    ): Result<NewsResponse> {
        return try {
            val result = newsService.searchArticles(pageSize, pageNumber, searchQuery)
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