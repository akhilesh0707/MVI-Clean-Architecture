package com.aqube.mvi.features.articledetail

import com.aqube.mvi.common.BaseViewModel
import com.aqube.mvi.common.mvi.ViewAction
import com.aqube.mvi.common.mvi.ViewIntent
import com.aqube.mvi.common.mvi.ViewState
import com.aqube.mvi.common.utils.CoroutineContextProvider
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

