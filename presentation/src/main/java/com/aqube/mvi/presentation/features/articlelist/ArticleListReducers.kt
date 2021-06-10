package com.aqube.mvi.presentation.features.articlelist

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.Article
/*

fun Result<List<Article>>.reduce(isSearchMode: Boolean = false): ArticleListState {
    return when (this) {
        is Result.Success -> {
            if (isSearchMode)
              //  ArticleListState.ResultSearchArticles(data)
            else
              //  ArticleListState.ResultAllArticles(data)
        }
        is Result.Error -> ArticleListState.Exception(exception)
        is Result.Loading -> ArticleListState.Loading
    }
}*/
