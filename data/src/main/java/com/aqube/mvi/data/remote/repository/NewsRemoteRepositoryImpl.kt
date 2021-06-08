package com.aqube.mvi.data.remote.repository

import com.aqube.mvi.data.remote.api.Constants.API_KEY
import com.aqube.mvi.data.remote.api.Constants.COUNTRY
import com.aqube.mvi.data.remote.api.Constants.PAGE_SIZE
import com.aqube.mvi.data.remote.api.NewsService
import com.aqube.mvi.domain.common.CallErrors
import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.extensions.applyCommonSideEffects
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.domain.repository.NewsRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(private val newsService: NewsService) :
    NewsRemoteRepository {
    override suspend fun getTopHeadings(): Flow<Result<List<Article>>> = flow {
        newsService.getTopHeadings(PAGE_SIZE, 1, COUNTRY, API_KEY).run {
            if (this.isSuccessful) {
                if (this.body() == null) {
                    emit(Result.Error(CallErrors.ErrorEmptyData))
                } else {
                    emit(Result.Success(this.body()!!.articles))
                }
            } else {
                emit(Result.Error(CallErrors.ErrorServer))
            }
        }
    }.applyCommonSideEffects().catch {
        emit(Result.Error(CallErrors.ErrorException(it)))
    }
}