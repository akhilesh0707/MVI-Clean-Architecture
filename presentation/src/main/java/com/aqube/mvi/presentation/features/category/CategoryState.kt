package com.aqube.mvi.presentation.features.category

import com.aqube.mvi.domain.common.CallErrors
import com.aqube.mvi.domain.model.categories.Category
import com.aqube.mvi.presentation.common.ViewState

sealed class CategoryState : ViewState {
    object Loading : CategoryState()
    data class ResultAllCategories(val data: List<Category>) : CategoryState()
    data class Exception(val callErrors: CallErrors) : CategoryState()
}