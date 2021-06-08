package com.aqube.mvi.features.articlelist.ui

import com.aqube.mvi.common.BaseViewModel
import com.aqube.mvi.common.utils.CoroutineContextProvider
import com.aqube.mvi.domain.interactor.GetTopHeadingListUseCase
import com.aqube.mvi.features.articlelist.ArticleListAction
import com.aqube.mvi.features.articlelist.ArticleListIntent
import com.aqube.mvi.features.articlelist.ArticleListState
import com.aqube.mvi.features.articlelist.reduce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val topHeadingListUseCase: GetTopHeadingListUseCase
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
                    topHeadingListUseCase(Unit).collect {
                        viewState.postValue(it.reduce())
                    }
                }
                is ArticleListAction.SearchArticle -> {

                }
            }
        }
    }
}

