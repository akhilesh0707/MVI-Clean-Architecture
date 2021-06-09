package com.aqube.mvi.presentation.features.articlelist

import com.aqube.mvi.presentation.common.ViewAction

sealed class ArticleListAction : ViewAction {
    data class SearchArticle(val name: String) : ArticleListAction()
    object AllArticles : ArticleListAction()
}