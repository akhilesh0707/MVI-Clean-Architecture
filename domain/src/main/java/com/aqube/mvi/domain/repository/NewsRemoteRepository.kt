package com.aqube.mvi.domain.repository

import com.aqube.mvi.domain.model.NewsResponse

interface NewsRemoteRepository {
    suspend fun getTopHeadings(): NewsResponse
}