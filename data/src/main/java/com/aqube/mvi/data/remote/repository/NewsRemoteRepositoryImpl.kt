package com.aqube.mvi.data.remote.repository

import com.aqube.mvi.data.remote.api.NewsService
import com.aqube.mvi.domain.model.NewsResponse
import com.aqube.mvi.domain.repository.NewsRemoteRepository
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(private val newsService: NewsService) :
    NewsRemoteRepository {
    override suspend fun getTopHeadings(
        pageSize: Int,
        pageNumber: Int,
        country: String
    ): NewsResponse {
        return newsService.getTopHeadings(pageSize, pageNumber, country)
    }
}