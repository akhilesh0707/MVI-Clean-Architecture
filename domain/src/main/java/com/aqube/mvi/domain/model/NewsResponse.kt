package com.aqube.mvi.domain.model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)