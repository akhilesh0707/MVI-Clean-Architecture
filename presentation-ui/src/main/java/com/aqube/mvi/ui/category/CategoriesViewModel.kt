package com.aqube.mvi.ui.category

import com.aqube.mvi.base.BaseViewModel
import com.aqube.mvi.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject


@HiltViewModel
class CategoriesViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")

}
