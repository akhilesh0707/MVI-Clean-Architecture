package com.aqube.mvi.presentation.features.articlelist

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.aqube.mvi.presentation.common.BaseViewModel
import com.aqube.mvi.presentation.common.utils.CoroutineContextProvider
import com.aqube.mvi.presentation.features.articlelist.pagination.ArticleDataSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val articleDataSource: ArticleDataSourceFactory
) : BaseViewModel<ArticleListIntent, ArticleListAction, ArticleListState>(contextProvider) {

    override fun intentToAction(intent: ArticleListIntent): ArticleListAction {
        return when (intent) {
            is ArticleListIntent.LoadAllArticles -> ArticleListAction.AllArticles
            is ArticleListIntent.ClearSearch -> ArticleListAction.AllArticles
            is ArticleListIntent.SearchArticle -> ArticleListAction.SearchArticle(intent.name)
        }
    }

    override fun handleAction(action: ArticleListAction) {
        launchCoroutineIO {
            when (action) {
                is ArticleListAction.AllArticles -> {
                    articleDataSource.getTopArticleList().cachedIn(this).collect {
                        viewState.postValue(ArticleListState.ResultAllArticles(it))
                    }
                }
                is ArticleListAction.SearchArticle -> {

                }
            }
        }
    }
}

