package com.aqube.mvi.domain.model.articles

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)