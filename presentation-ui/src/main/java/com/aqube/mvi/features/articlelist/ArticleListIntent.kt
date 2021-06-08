package com.aqube.mvi.features.articlelist

import com.aqube.mvi.common.mvi.ViewIntent

sealed class ArticleListIntent : ViewIntent {
    object LoadAllArticles : ArticleListIntent()
    data class SearchArticle(val name: String) : ArticleListIntent()
    object ClearSearch : ArticleListIntent()
}