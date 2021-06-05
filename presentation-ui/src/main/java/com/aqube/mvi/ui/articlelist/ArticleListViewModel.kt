package com.aqube.mvi.ui.articlelist

import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.domain.interactor.GetTopHeadingListUseCase
import com.aqube.mvi.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val getTopHeadingListUseCase: GetTopHeadingListUseCase
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")

    fun getTopHeadings() {
        launchCoroutineIO {
            loadTopHeadings()
        }
    }

    private suspend fun loadTopHeadings() {
        getTopHeadingListUseCase(Unit).collect {
            // Timber.d(it)
        }
    }
}

