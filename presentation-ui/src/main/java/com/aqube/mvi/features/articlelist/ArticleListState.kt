package com.aqube.mvi.features.articlelist

import com.aqube.mvi.common.mvi.ViewState
import com.aqube.mvi.domain.common.CallErrors
import com.aqube.mvi.domain.model.Article

sealed class ArticleListState : ViewState {
    object Loading : ArticleListState()
    data class ResultAllArticles(val data: List<Article>) : ArticleListState()
    data class ResultSearchArticles(val data: List<Article>) : ArticleListState()
    data class Exception(val callErrors: CallErrors) : ArticleListState()
}