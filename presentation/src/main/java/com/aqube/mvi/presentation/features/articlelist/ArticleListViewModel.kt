package com.aqube.mvi.presentation.features.articlelist

import androidx.paging.cachedIn
import com.aqube.mvi.presentation.common.BaseViewModel
import com.aqube.mvi.presentation.common.utils.CoroutineContextProvider
import com.aqube.mvi.presentation.pagination.ArticleDataSourceFactory
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
            is ArticleListIntent.LoadSelectedCategory -> ArticleListAction.SelectedCategoryArticles(intent.category)
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
                    articleDataSource.searchArticles(action.searchQuery).cachedIn(this).collect {
                        viewState.postValue(ArticleListState.ResultAllArticles(it))
                    }
                }
                is ArticleListAction.SelectedCategoryArticles -> {
                    articleDataSource.getCategoryArticles(action.category).cachedIn(this).collect {
                        viewState.postValue(ArticleListState.ResultAllArticles(it))
                    }
                }
            }
        }
    }
}

