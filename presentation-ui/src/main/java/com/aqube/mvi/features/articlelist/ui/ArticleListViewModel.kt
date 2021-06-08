package com.aqube.mvi.features.articlelist.ui

import com.aqube.mvi.common.BaseViewModel
import com.aqube.mvi.domain.interactor.GetTopHeadingListUseCase
import com.aqube.mvi.common.utils.CoroutineContextProvider
import com.aqube.mvi.features.articlelist.ArticleListAction
import com.aqube.mvi.features.articlelist.ArticleListIntent
import com.aqube.mvi.features.articlelist.ArticleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
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
                    topHeadingListUseCase.invoke(Unit).let {
                        Timber.d("Inside ViewModel $it")
                    }
                    //dataManager.getAllCharacters().collect {
                    //   mState.postValue(it.reduce())
                    //}
                }
                is ArticleListAction.SearchArticle -> {
                    //dataManager.searchCharacters(action.name).collect {
                    //    mState.postValue(it.reduce(true))
                    // }
                }
            }
        }
    }
}

