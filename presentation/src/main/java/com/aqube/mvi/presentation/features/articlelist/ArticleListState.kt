package com.aqube.mvi.presentation.features.articlelist

import androidx.paging.PagingData
import com.aqube.mvi.domain.common.CallErrors
import com.aqube.mvi.domain.model.Article
import com.aqube.mvi.presentation.common.ViewState

sealed class ArticleListState : ViewState {
    object Loading : ArticleListState()
    data class ResultAllArticles(val data: PagingData<Article>) : ArticleListState()
    data class ResultSearchArticles(val data: PagingData<Article>) : ArticleListState()
    data class Exception(val callErrors: CallErrors) : ArticleListState()
}