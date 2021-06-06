package com.aqube.mvi.data.remote.repository

import com.aqube.mvi.data.remote.api.Constants.API_KEY
import com.aqube.mvi.data.remote.api.Constants.COUNTRY
import com.aqube.mvi.data.remote.api.Constants.PAGE_SIZE
import com.aqube.mvi.data.remote.api.NewsService
import com.aqube.mvi.domain.model.NewsResponse
import com.aqube.mvi.domain.repository.NewsRemoteRepository
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(private val newsService: NewsService) :
    NewsRemoteRepository {
    override suspend fun getTopHeadings(): NewsResponse {
        return newsService.getTopHeadings(PAGE_SIZE, 1, COUNTRY, API_KEY)
    }
}