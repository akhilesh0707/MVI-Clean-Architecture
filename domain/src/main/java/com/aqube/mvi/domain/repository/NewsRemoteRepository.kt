package com.aqube.mvi.domain.repository

import com.aqube.mvi.domain.model.NewsResponse

interface NewsRemoteRepository {
    suspend fun getTopHeadings(
        pageSize: Int,
        pageNumber: Int,
        country: String
    ): NewsResponse
}