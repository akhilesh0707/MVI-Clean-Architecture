package com.aqube.mvi.presentation.features.articlelist

import com.aqube.mvi.presentation.common.ViewIntent

sealed class ArticleListIntent : ViewIntent {
    object LoadAllArticles : ArticleListIntent()
    data class SearchArticle(val name: String) : ArticleListIntent()
    object ClearSearch : ArticleListIntent()
    data class LoadSelectedCategory(val category: String) : ArticleListIntent()
}