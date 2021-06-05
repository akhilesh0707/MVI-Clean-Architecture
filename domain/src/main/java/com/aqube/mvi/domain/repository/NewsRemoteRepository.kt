package com.aqube.mvi.domain.repository

import com.aqube.mvi.data.model.NewsResponse

interface NewsRemoteRepository {
    suspend fun getTopHeadings(): NewsResponse
}