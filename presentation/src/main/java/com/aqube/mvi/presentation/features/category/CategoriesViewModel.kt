package com.aqube.mvi.presentation.features.category

import androidx.lifecycle.ViewModel
import com.aqube.mvi.presentation.common.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider
) : ViewModel() {


}

