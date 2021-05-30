package com.aqube.mvi.data.remote.repository

import com.aqube.mvi.data.model.NewsResponse

interface NewsRemoteRepository {
    suspend fun getTopHeadings(): NewsResponse
}