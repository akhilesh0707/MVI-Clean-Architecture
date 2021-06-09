package com.aqube.mvi.presentation.features.articledetail

import com.aqube.mvi.presentation.common.utils.CoroutineContextProvider
import com.aqube.mvi.presentation.common.BaseViewModel
import com.aqube.mvi.presentation.common.ViewAction
import com.aqube.mvi.presentation.common.ViewIntent
import com.aqube.mvi.presentation.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider
) : BaseViewModel<ViewIntent, ViewAction, ViewState>(contextProvider) {
    override fun intentToAction(intent: ViewIntent): ViewAction {
        TODO("Not yet implemented")
    }

    override fun handleAction(action: ViewAction) {
        TODO("Not yet implemented")
    }
}

