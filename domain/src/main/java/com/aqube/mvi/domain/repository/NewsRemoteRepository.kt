package com.aqube.mvi.domain.repository

interface NewsRemoteRepository {
    suspend fun getTopHeadings(): Void
}