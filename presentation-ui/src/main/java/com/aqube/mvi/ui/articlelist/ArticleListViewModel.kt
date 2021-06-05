package com.aqube.mvi.ui.articlelist

import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.domain.interactor.GetTopHeadingListUseCase
import com.aqube.mvi.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val topHeadingListUseCase: GetTopHeadingListUseCase
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.d(exception)
    }
    fun getTopHeadings() {
        launchCoroutineIO {
            loadTopHeadings()
        }
    }

    private suspend fun loadTopHeadings() {
        topHeadingListUseCase.invoke(Unit)
    }
}

