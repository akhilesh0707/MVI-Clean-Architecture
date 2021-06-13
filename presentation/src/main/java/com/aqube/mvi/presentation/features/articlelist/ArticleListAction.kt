package com.aqube.mvi.presentation.features.articlelist

import com.aqube.mvi.presentation.common.ViewAction

sealed class ArticleListAction : ViewAction {
    data class SearchArticle(val searchQuery: String) : ArticleListAction()
    object AllArticles : ArticleListAction()
    data class SelectedCategoryArticles(val category: String) : ArticleListAction()
}