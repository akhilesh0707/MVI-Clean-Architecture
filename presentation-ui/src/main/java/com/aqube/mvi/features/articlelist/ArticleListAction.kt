package com.aqube.mvi.features.articlelist

import com.aqube.mvi.common.mvi.ViewAction

sealed class ArticleListAction : ViewAction {
    data class SearchArticle(val name: String) : ArticleListAction()
    object AllArticles : ArticleListAction()
}