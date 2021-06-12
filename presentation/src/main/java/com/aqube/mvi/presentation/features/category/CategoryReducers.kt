package com.aqube.mvi.presentation.features.category

import com.aqube.mvi.domain.common.Result
import com.aqube.mvi.domain.model.categories.Category

fun Result<List<Category>>.reduce(): CategoryState {
    return when (this) {
        is Result.Success -> {
            CategoryState.ResultAllCategories(data)
        }
        is Result.Error -> CategoryState.Exception(exception)
        is Result.Loading -> CategoryState.Loading
    }
}
